package ru.otus.istyazhkina.testapp.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalisationServiceImpl implements LocalizationService {

    private Locale locale = Locale.forLanguageTag("en-EN");
    private final MessageSource messageSource;

    public LocalisationServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String getMessageByKey(String key, Object... objects) {
        return messageSource.getMessage(key, objects, locale);

    }
}
