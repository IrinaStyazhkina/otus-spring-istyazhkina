package ru.otus.istyazhkina.testapp.service;

import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.testapp.config.LanguageConfig;

import java.util.Locale;
import java.util.Map;

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
        final Map<Locale, String> languages = languageConfig.getLanguages();
        for (Map.Entry<Locale, String> entry : languages.entrySet()) {
            ioService.write(entry.getKey().getLanguage());
        }
        Locale locale = null;
        while (locale == null) {
            locale = getLanguage(languages);
        }
        languageConfig.setLocale(locale);
    }

    Locale getLanguage(Map<Locale, String> languages) {
        String lang = ioService.read();
        for (Map.Entry<Locale, String> entry : languages.entrySet()) {
            if (entry.getKey().getLanguage().equals(lang)) {
                return entry.getKey();
            }
        }
        printTranslatedMessagesFacade.printTranslated("app.language.error");
        return null;
    }
}
