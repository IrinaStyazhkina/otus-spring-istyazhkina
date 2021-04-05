package ru.otus.istyazhkina.testapp.service;

import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.testapp.config.Language;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final IOService ioService;
    private final LocalizationService localizationService;

    public LanguageServiceImpl(IOService ioService, LocalizationService localizationService) {
        this.ioService = ioService;
        this.localizationService = localizationService;
    }

    @Override
    public void chooseAppLanguage() {
        ioService.write(localizationService.getMessageByKey("app.language.choose"));
        for (Language language : Language.values()) {
            ioService.write(language.name());
        }

        Language language = null;
        while (language == null) {
            language = getLanguage();
        }
        localizationService.setLocale(language.getLocale());
    }

    private Language getLanguage() {
        String lang = ioService.read();
        for (Language language : Language.values()) {
            if (language.name().equals(lang)) {
                return language;
            }
        }
        ioService.write(localizationService.getMessageByKey("app.language.error"));
        return null;
    }
}
