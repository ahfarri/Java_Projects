package com.farris.beltexam.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.farris.beltexam.models.Idea;
import com.farris.beltexam.models.LoginUser;
import com.farris.beltexam.models.User;
import com.farris.beltexam.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "loginpage.jsp";
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        userService.register(newUser, result);
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "loginpage.jsp";
        }
        session.setAttribute("user_id", newUser.getId());
        session.setAttribute("user", newUser.getUserName());
        return "redirect:/ideas";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        User user = userService.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "loginpage.jsp";
        }
        session.setAttribute("user_id", user.getId());
        session.setAttribute("user", user.getUserName());
        return "redirect:/ideas";
    }
    
    @GetMapping("/ideas")
    public String index(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    	Long UserId = (Long)session.getAttribute("user_id");
		if(UserId == null) {
			redirectAttributes.addFlashAttribute("notAllowed","You must log in first!");
			return "redirect:/";
		}
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("session", session.getAttribute("user_id"));
        List<Idea> all = userService.allLikes();
        List<User> users = userService.allUsers();
        model.addAttribute("users", users);
        model.addAttribute("ideas", all);
        return "dashboard.jsp";
    
    }
    
    @GetMapping("/likes/{id}")
    public String likeIdea(@PathVariable("id") Long id, Model model, HttpSession session) {
    	
    	userService.createRelationship(id, (Long) session.getAttribute("user_id"));
    	List<Idea> all = userService.allLikes();
        model.addAttribute("ideas", all);
        model.addAttribute("session", session.getAttribute("user_id"));
        List<User> users = userService.allUsers();
        model.addAttribute("users", users);
        return "redirect:/ideas";
    
    }
    
    @GetMapping("/unlikes/{id}")
    public String unlikeIdea(@PathVariable("id") Long id, Model model, HttpSession session) {
    	userService.removeRelationship(id, (Long) session.getAttribute("user_id"));
    	List<Idea> all = userService.allLikes();
        model.addAttribute("ideas", all);
        model.addAttribute("session", session.getAttribute("user_id"));
        List<User> users = userService.allUsers();
        model.addAttribute("users", users);
        return "redirect:/ideas";
    
    }
    
    @GetMapping("/ideas/new")
    public String ideaNew(@ModelAttribute("newIdea") Idea newIdea, HttpSession session, RedirectAttributes redirectAttributes) {
    	Long UserId = (Long)session.getAttribute("user_id");
		if(UserId == null) {
			redirectAttributes.addFlashAttribute("notAllowed","You must log in first!");
			return "redirect:/";
		}
        return "newidea.jsp";
    
    }
    
    @PostMapping("/ideas/create")
    public String ideaCreate(@Valid @ModelAttribute("newIdea") Idea newIdea, BindingResult result, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "newidea.jsp";
		}
        model.addAttribute("user_id", session.getAttribute("user_id"));
        userService.createIdea(newIdea);
        return "redirect:/ideas";
    
    }
    
    @GetMapping("/ideas/{id}")
    public String viewIdea(@PathVariable("id") Long id, Model model, HttpSession session) {
    	Idea idea = userService.findIdeabyId(id);
    	User user = userService.findUserById(idea.getCreator());
    	List<User> liked = idea.getUsers();
    	model.addAttribute("idea", idea);
    	model.addAttribute("users", liked);
    	model.addAttribute("user", user.getUserName());
    	model.addAttribute("session", session.getAttribute("user_id"));
        return "viewpage.jsp";
    
    }
    
    @GetMapping("/ideas/{id}/edit")
    public String editpage(@PathVariable("id") Long id, @ModelAttribute("editIdea") Idea editIdea, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    	Long UserId = (Long)session.getAttribute("user_id");
    	Idea idea = userService.findIdeabyId(id);
		if(UserId != idea.getCreator()) {
			redirectAttributes.addFlashAttribute("notAllowed","You do not have access to this page.");
			return "redirect:/ideas";
		}
    	model.addAttribute("idea", idea);
    	model.addAttribute("user_id", session.getAttribute("user_id"));
        return "editpage.jsp";
    
    }
    
    @PostMapping("/ideas/{id}/update")
    public String editIdea(@PathVariable("id") Long id, @Valid @ModelAttribute("editIdea") Idea editIdea, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			Idea idea = userService.findIdeabyId(id);
	    	model.addAttribute("idea", idea);
			return "editpage.jsp";
		}
        model.addAttribute("user_id", session.getAttribute("user_id"));
        userService.updateIdea(editIdea);
        return "redirect:/ideas";
    
    }
    
    @GetMapping("/low/likes")
    public String low(Model model, HttpSession session) {
    	List<Idea> ideas = userService.allLikesAsc();
    	model.addAttribute("ideas", ideas);
    	model.addAttribute("user_id", session.getAttribute("user_id"));
    	List<User> users = userService.allUsers();
        model.addAttribute("users", users);
    	return "dashboard.jsp";
    
    }
    
    @GetMapping("/high/likes")
    public String high(Model model, HttpSession session) {
    	List<Idea> ideas = userService.allLikesDesc();
    	model.addAttribute("ideas", ideas);
    	model.addAttribute("user_id", session.getAttribute("user_id"));
    	List<User> users = userService.allUsers();
        model.addAttribute("users", users);
    	return "dashboard.jsp";
    
    }
    
    @RequestMapping("/delete/{id}")
 	public String deleteIdea(@PathVariable("id") Long id) {
 			userService.deleteIdea(id);
 			return "redirect:/ideas";
    }
    
    @RequestMapping("/logout")
 	public String logout(HttpSession session) {
 			session.invalidate();
 			return "redirect:/";
    }
    
    
}
