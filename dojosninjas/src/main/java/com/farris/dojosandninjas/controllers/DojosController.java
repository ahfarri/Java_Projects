package com.farris.dojosandninjas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.farris.dojosandninjas.models.Dojo;
import com.farris.dojosandninjas.models.Ninja;
import com.farris.dojosandninjas.services.DojosService;

@Controller
public class DojosController {
	private final DojosService dojosService;
	
    public DojosController(DojosService dojosService){
        this.dojosService = dojosService;
    }
    
    @GetMapping("/dojos/new")
    public String dojospage(@ModelAttribute ("dojo") Dojo dojo) {
    	return "newdojo.jsp";
    }
    
    @PostMapping("/dojos/create")
    public String createD(@Valid @ModelAttribute ("dojo") Dojo dojo, BindingResult result) {
    	 if (result.hasErrors()) {
             return "newdojo.jsp";
         } else {
        	 dojosService.createDojo(dojo);
             return "redirect:/dojos/new";}
    }
    
    @GetMapping("/ninjas/new")
    public String ninjaspage(@ModelAttribute ("ninja") Ninja ninja, Model model) {
    	List dojos = dojosService.allDojos();
    	model.addAttribute("dojos", dojos);
    	return "newninja.jsp";
    }
    
    @PostMapping("/ninjas/create")
    public String createN(@Valid @ModelAttribute ("ninja") Ninja ninja, BindingResult result, Model model) {
    	if (result.hasErrors()) {
            return "newninja.jsp";
        } else {
        	dojosService.createNinja(ninja);;
            return "redirect:/ninjas/new";}
    }
    
    @RequestMapping("/dojos/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Dojo dojo = dojosService.findDojo(id);
        model.addAttribute("dojo", dojo);
        return "showdojo.jsp";
    }
}
