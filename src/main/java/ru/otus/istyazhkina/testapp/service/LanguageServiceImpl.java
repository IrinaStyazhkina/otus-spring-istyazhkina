package ru.otus.istyazhkina.testapp.service;

import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.testapp.config.LanguageConfig;

import java.util.List;
import java.util.Locale;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageConfig languageConfig;
    private final IOService ioService;
    private final PrintTranslatedMessagesFacade printTranslatedMessagesFacade;

    public LanguageServiceImpl(LanguageConfig languageConfig, PrintTranslatedMessagesFacade printTranslatedMessagesFacade, IOService ioService) {
        this.languageConfig = languageConfig;
        this.printTranslatedMessagesFacade = printTranslatedMessagesFacade;
        this.ioService = ioService;
    }

    @Override
    public void chooseAppLanguage() {
        printTranslatedMessagesFacade.printTranslated("app.language.choose");
        final List<Locale> languages = languageConfig.getLanguages();
        for (Locale lang : languages) {
            ioService.write(lang.getLanguage());
        }
        Locale locale = null;
        while (locale == null) {
            locale = getLanguage(languages);
        }
        languageConfig.setLocale(locale);
    }

    Locale getLanguage(List<Locale> languages) {
        String language = ioService.read();
        for (Locale lang : languages) {
            if (lang.getLanguage().equals(language)) {
                return lang;
            }
        }
        printTranslatedMessagesFacade.printTranslated("app.language.error");
        return null;
    }
}
