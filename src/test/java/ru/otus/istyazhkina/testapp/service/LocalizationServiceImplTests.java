package ru.otus.istyazhkina.testapp.service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.istyazhkina.testapp.config.LanguageConfig;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Execution(ExecutionMode.SAME_THREAD)
public class LocalizationServiceImplTests {

    @Autowired
    private LocalizationService localizationService;

    @Autowired
    private LanguageConfig languageConfig;

    @Test
    @DisplayName("Вывод сообщений на русском языке")
    void shouldGetMessageByKeyForRuLocale() {
        languageConfig.setLocale(Locale.forLanguageTag("ru-RU"));
        String messageByKey = localizationService.getMessageByKey("app.student.greeting");
        assertEquals("Добро пожаловать на платформу для тестирования!", messageByKey);
    }

    @Test
    @DisplayName("Вывод сообщений на английском языке")
    void shouldGetMessageByKeyForEnLocale() {
        languageConfig.setLocale(Locale.forLanguageTag("en-EN"));
        String messageByKey = localizationService.getMessageByKey("app.student.greeting");
        assertEquals("Welcome to the testing platform!", messageByKey);
    }
}
