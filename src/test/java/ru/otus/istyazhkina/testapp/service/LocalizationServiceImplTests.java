package ru.otus.istyazhkina.testapp.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.istyazhkina.testapp.config.Language.ENGLISH;
import static ru.otus.istyazhkina.testapp.config.Language.RUSSIAN;

@SpringBootTest
@Execution(ExecutionMode.SAME_THREAD)
public class LocalizationServiceImplTests {

    @Autowired
    private LocalizationService localizationService;

    @Test
    @DisplayName("Вывод сообщений на русском языке")
    void shouldGetMessageByKeyForRuLocale() {
        localizationService.setLocale(RUSSIAN.getLocale());
        String messageByKey = localizationService.getMessageByKey("app.student.greeting");
        assertEquals("Добро пожаловать на платформу для тестирования!", messageByKey);
    }

    @Test
    @DisplayName("Вывод сообщений на английском языке")
    void shouldGerMessageByKeyForEnLocale() {
        localizationService.setLocale(ENGLISH.getLocale());
        String messageByKey = localizationService.getMessageByKey("app.student.greeting");
        assertEquals("Welcome to the testing platform!", messageByKey);
    }
}
