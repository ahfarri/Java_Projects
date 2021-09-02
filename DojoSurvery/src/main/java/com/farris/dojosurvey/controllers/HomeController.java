package com.farris.dojosurvey.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@RequestMapping("/")
		public String home() {
		return "Index.jsp";
	}
	
	@PostMapping("/create")
	public String showResult(@RequestParam("yourname") String yourname, @RequestParam("comment") String comment, @RequestParam("language") String language, @RequestParam("location") String location, Model model ) {
		
		
		model.addAttribute("yourname", yourname);
		model.addAttribute("comment", comment);
		model.addAttribute("lang", language);
		model.addAttribute("location", location);
		
		return "result.jsp";
	}
}
