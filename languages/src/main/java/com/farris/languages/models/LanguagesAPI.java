package com.farris.languages.models;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farris.languages.services.LanguageService;
import com.farris.languages.models.Language;

@RestController
public class LanguagesAPI {
	private final LanguageService languageService;
	
    public LanguagesAPI(LanguageService languageService){
        this.languageService = languageService;
    }

    @RequestMapping("/api/languages")
    public List<Language> index() {
        return languageService.allLanguages();
    }
    
    @PostMapping("/api/languages")
    public Language create(@RequestParam(value="name") String name, @RequestParam(value="creator") String creat, @RequestParam(value="currentVersion") String vers) {
        Language language = new Language(name, creat, vers);
        return languageService.createLanguage(language);
    }
    
    @RequestMapping("/api/languages/{id}")
    public Language show(@PathVariable("id") Long id) {
        Language language = languageService.findLanguage(id);
        return language;
    }
    @PostMapping("/api/languages/edit/{id}")
    public Language update(@PathVariable("id") Long id, @RequestParam(value="name") String name, @RequestParam(value="creator") String creat, @RequestParam(value="currentVersion") String vers) {
    	Language l = languageService.findLanguage(id);
    	l.setName(name);
    	l.setCreator(creat);
    	l.setCurrentVersion(vers);
    	
        return languageService.updateLanguage(l);
    }
    @RequestMapping("/api/languages/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.languageService.deleteLanguage(id);
    }
}