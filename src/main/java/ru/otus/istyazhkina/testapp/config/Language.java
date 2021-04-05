package ru.otus.istyazhkina.testapp.config;

import java.util.Locale;

public enum Language {
    RUSSIAN(Locale.forLanguageTag("ru-RU"), "questions/questions_ru_RU.csv"),
    ENGLISH(Locale.forLanguageTag("en-EN"), "questions/questions_en_EN.csv");

    Language(Locale locale, String pathToCsv) {
        this.locale = locale;
        this.pathToCsv = pathToCsv;
    }

    private final Locale locale;
    private final String pathToCsv;

    public Locale getLocale() {
        return locale;
    }

    public String getPathToCsv() {
        return pathToCsv;
    }

    public static Language getByLocale(Locale locale) {
        for (Language lang : Language.values()) {
            if (lang.getLocale().equals(locale)) {
                return lang;
            }
        }
        throw new IllegalArgumentException("Unexpected locale");
    }
}
