package ru.otus.istyazhkina.testapp.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.testapp.config.LanguageConfig;

@Service
public class LocalisationServiceImpl implements LocalizationService {

    private final MessageSource messageSource;
    private final LanguageConfig languageConfig;

    public LocalisationServiceImpl(MessageSource messageSource, LanguageConfig languageConfig) {
        this.messageSource = messageSource;
        this.languageConfig = languageConfig;
    }

    @Override
    public String getMessageByKey(String key, Object... objects) {
        return messageSource.getMessage(key, objects, languageConfig.getLocale());
    }
}
