package ru.otus.istyazhkina.testapp.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class LanguageServiceImplTests {

    @Autowired
    private LanguageServiceImpl languageService;

    @MockBean
    private IOService ioService;

    private static Map<Locale, String> supportedLanguages = new HashMap<>();

    @BeforeAll
    static void setUp() {
        supportedLanguages.put(Locale.forLanguageTag("ru-RU"), "questions/questions_ru_RU");
    }

    @Test
    @DisplayName("Если язык не поддерживается, метод getLanguage() должен вернуть null")
    void shouldReturnNullIfUnsupportedLanguage() {
        Mockito.when(ioService.read())
                .thenReturn("de");
        assertNull(languageService.getLanguage(supportedLanguages));
    }

    @Test
    @DisplayName("Если язык поддерживается, метод getLanguage() возвращает соответствующую локаль")
    void shouldReturnLocaleForSupportedLanguage() {
        Mockito.when(ioService.read())
                .thenReturn("ru");
        assertEquals(Locale.forLanguageTag("ru-RU"), languageService.getLanguage(supportedLanguages));
    }
}
