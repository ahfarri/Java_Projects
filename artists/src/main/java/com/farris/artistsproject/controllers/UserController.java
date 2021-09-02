package com.farris.artistsproject.controllers;

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

import com.farris.artistsproject.models.LoginUser;
import com.farris.artistsproject.models.User;
import com.farris.artistsproject.models.Work;
import com.farris.artistsproject.services.UserService;

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
        session.setAttribute("user", newUser.getFirstName());
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
        session.setAttribute("user", user.getFirstName());
        return "redirect:/ideas";
    }
    
    @GetMapping("/works")
    public String index(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    	Long UserId = (Long)session.getAttribute("user_id");
		if(UserId == null) {
			redirectAttributes.addFlashAttribute("notAllowed","You must log in first!");
			return "redirect:/";
		}
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("session", session.getAttribute("user_id"));
        List<Work> all = userService.allLikes();
        List<User> users = userService.allUsers();
        model.addAttribute("users", users);
        model.addAttribute("ideas", all);
        return "dashboard.jsp";
    
    }
    
    @GetMapping("/likes/{id}")
    public String likeIdea(@PathVariable("id") Long id, Model model, HttpSession session) {
    	
    	userService.createRelationship(id, (Long) session.getAttribute("user_id"));
    	List<Work> all = userService.allLikes();
        model.addAttribute("ideas", all);
        model.addAttribute("session", session.getAttribute("user_id"));
        List<User> users = userService.allUsers();
        model.addAttribute("users", users);
        return "redirect:/ideas";
    
    }
    
    @GetMapping("/unlikes/{id}")
    public String unlikeIdea(@PathVariable("id") Long id, Model model, HttpSession session) {
    	userService.removeRelationship(id, (Long) session.getAttribute("user_id"));
    	List<Work> all = userService.allLikes();
        model.addAttribute("ideas", all);
        model.addAttribute("session", session.getAttribute("user_id"));
        List<User> users = userService.allUsers();
        model.addAttribute("users", users);
        return "redirect:/ideas";
    
    }
    
    @GetMapping("/works/new")
    public String ideaNew(@ModelAttribute("newWork") Work newWork, HttpSession session, RedirectAttributes redirectAttributes) {
    	Long UserId = (Long)session.getAttribute("user_id");
		if(UserId == null) {
			redirectAttributes.addFlashAttribute("notAllowed","You must log in first!");
			return "redirect:/";
		}
        return "newidea.jsp";
    
    }
    
    @PostMapping("/works/create")
    public String ideaCreate(@Valid @ModelAttribute("newWork") Work newWork, BindingResult result, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "newidea.jsp";
		}
        model.addAttribute("user_id", session.getAttribute("user_id"));
        userService.createWork(newWork);
        return "redirect:/ideas";
    
    }
    
    @GetMapping("/works/{id}")
    public String viewIdea(@PathVariable("id") Long id, Model model, HttpSession session) {
    	Work work = userService.findWorkbyId(id);
    	User user = userService.findUserById(work.getCreator());
    	List<User> liked = work.getUsers();
    	model.addAttribute("work", work);
    	model.addAttribute("users", liked);
    	model.addAttribute("user", user.getFirstName());
    	model.addAttribute("session", session.getAttribute("user_id"));
        return "viewpage.jsp";
    
    }
    
    @GetMapping("/works/{id}/edit")
    public String editpage(@PathVariable("id") Long id, @ModelAttribute("editWork") Work editWork, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    	Long UserId = (Long)session.getAttribute("user_id");
    	Work work = userService.findWorkbyId(id);
		if(UserId != work.getCreator()) {
			redirectAttributes.addFlashAttribute("notAllowed","You do not have access to this page.");
			return "redirect:/ideas";
		}
    	model.addAttribute("work", work);
    	model.addAttribute("user_id", session.getAttribute("user_id"));
        return "editpage.jsp";
    
    }
    
    @PostMapping("/works/{id}/update")
    public String editIdea(@PathVariable("id") Long id, @Valid @ModelAttribute("editWork") Work editWork, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			Work work = userService.findWorkbyId(id);
	    	model.addAttribute("work", work);
			return "editpage.jsp";
		}
        model.addAttribute("user_id", session.getAttribute("user_id"));
        userService.updateWork(editWork);
        return "redirect:/ideas";
    
    }
    
    @GetMapping("/low/likes")
    public String low(Model model, HttpSession session) {
    	List<Work> works = userService.allLikesAsc();
    	model.addAttribute("works", works);
    	model.addAttribute("user_id", session.getAttribute("user_id"));
    	List<User> users = userService.allUsers();
        model.addAttribute("users", users);
    	return "dashboard.jsp";
    
    }
    
    @GetMapping("/high/likes")
    public String high(Model model, HttpSession session) {
    	List<Work> works = userService.allLikesDesc();
    	model.addAttribute("works", works);
    	model.addAttribute("user_id", session.getAttribute("user_id"));
    	List<User> users = userService.allUsers();
        model.addAttribute("users", users);
    	return "dashboard.jsp";
    
    }
    
    @RequestMapping("/delete/{id}")
 	public String deleteWork(@PathVariable("id") Long id) {
 			userService.deleteWork(id);
 			return "redirect:/ideas";
    }
    
    @RequestMapping("/logout")
 	public String logout(HttpSession session) {
 			session.invalidate();
 			return "redirect:/";
    }
    
    
}
