package com.farris.languages.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.farris.languages.models.Language;
import com.farris.languages.services.LanguageService;

@Controller
public class LanguagesController {
	
private final LanguageService languageService;
    
    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }
    
    @GetMapping("/languages")
    public String index(Model model) {
        List<Language> languages = languageService.allLanguages();
        model.addAttribute("languages", languages);
        Language language = new Language();
        model.addAttribute("language", language);
        return "/languages/index.jsp";
    }
    
    @PostMapping(value="/languages")
    public String create(@Valid @ModelAttribute("language") Language language, BindingResult result) {
        if (result.hasErrors()) {
            return "/languages/index.jsp";
        } else {
            languageService.createLanguage(language);
            return "redirect:/languages";
        }
    }

    @GetMapping("/languages/{id}")
    public String getLanguage(@PathVariable("id") Long id, Model model ) {
    	Language l = languageService.findLanguage(id);
    	model.addAttribute("language", l);
        return "/languages/show.jsp";
    }
    @GetMapping("/languages/edit/{id}")
    public String editpage(@PathVariable("id") Long id, Model model) {
    	Language l = languageService.findLanguage(id);
    	model.addAttribute("language", l);
        return "/languages/edit.jsp";
    }
    @PostMapping("/languages/{id}")
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("language") Language language, BindingResult result) {
    	if (result.hasErrors()) {
            return "/languages/edit.jsp";
        } else {
            languageService.updateLanguage(language);
        }
        return "redirect:/languages";
    }

    @GetMapping("/languages/delete/{id}")
    public String deleteLanguage(@PathVariable("id") Long id) {
    	this.languageService.deleteLanguage(id);
        return "redirect:/languages";
    }
}

