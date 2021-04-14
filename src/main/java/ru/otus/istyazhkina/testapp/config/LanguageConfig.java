package ru.otus.istyazhkina.testapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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

    public List<Locale> getLanguages() {
        ArrayList<Locale> langs = new ArrayList<>();
        for (Map.Entry<Locale, String> entry : languages.entrySet()) {
            langs.add(entry.getKey());
        }
        return langs;
    }

    public void setLanguages(Map<Locale, String> languages) {
        this.languages = languages;
    }
}
