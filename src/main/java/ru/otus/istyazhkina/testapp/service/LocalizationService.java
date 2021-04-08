package ru.otus.istyazhkina.testapp.service;


public interface LocalizationService {

    String getMessageByKey(String key, Object... objects);

}
