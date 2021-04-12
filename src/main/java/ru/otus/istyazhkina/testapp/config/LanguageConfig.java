package ru.otus.istyazhkina.testapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@ConfigurationProperties(prefix = "application")
@Component
public class LanguageConfig {

    private Locale locale;
    private Map<Locale, String> languages;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getQuestionsFile() {
        return languages.get(locale);
    }

    public Map<Locale, String> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<Locale, String> languages) {
        this.languages = languages;
    }
}
