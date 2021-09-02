package com.farris.languages.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.farris.languages.repositories.LanguageRepository;
import com.farris.languages.models.Language;

@Service
public class LanguageService {

    // adding the book repository as a dependency
    private final LanguageRepository languageRepository;
    
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }
    // returns all the languages
    public List<Language> allLanguages() {
        return (List<Language>) languageRepository.findAll();
    }
    // creates a language
    public Language createLanguage(Language l) {
        return languageRepository.save(l);
    }
    // retrieves a language
    public Language findLanguage(Long id) {
        return this.languageRepository.findById(id).orElse(null);
    }
    public Language updateLanguage(Language l) {
        return languageRepository.save(l);
    }
    public void deleteLanguage(Long id) {
        this.languageRepository.deleteById(id);
    }

}
