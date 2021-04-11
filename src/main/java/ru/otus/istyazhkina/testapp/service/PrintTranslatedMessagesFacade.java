package ru.otus.istyazhkina.testapp.service;

import org.springframework.stereotype.Service;

@Service
public class PrintTranslatedMessagesFacade {

    private final LocalizationService localizationService;
    private final IOService ioService;

    public PrintTranslatedMessagesFacade(LocalizationService localizationService, IOService ioService) {
        this.localizationService = localizationService;
        this.ioService = ioService;
    }

    public void printTranslated(String key, Object... objects) {
        ioService.write(localizationService.getMessageByKey(key, objects));
    }
}
