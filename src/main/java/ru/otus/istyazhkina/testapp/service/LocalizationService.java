package ru.otus.istyazhkina.testapp.service;

import java.util.Locale;

public interface LocalizationService {

    String getMessageByKey(String key, Object... objects);

    Locale getLocale();

    void setLocale(Locale locale);

}
