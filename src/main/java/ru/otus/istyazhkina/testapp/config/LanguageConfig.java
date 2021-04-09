package ru.otus.istyazhkina.testapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ConfigurationProperties(prefix = "application")
@Component
public class LanguageConfig {

    private Map<Locale, String> languages;

    public void setLocale(Locale locale) {
        Locale.setDefault(locale);
    }

    public String getQuestionsFile() {
        return languages.get(Locale.getDefault());
    }

    public Map<Locale, String> getLanguages() {
        return languages;
    }

    public void setLanguages(HashMap<Locale, String> languages) {
        this.languages = languages;
    }
}
