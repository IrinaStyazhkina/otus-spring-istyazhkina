package ru.otus.istyazhkina.testapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TestingServiceImplTests {

    @Value("${application.points-to-pass}")
    private int pointsToPass;

    @Autowired
    TestingServiceImpl testingService;

    @Test
    void shouldFailTestIfCorrectAnswersLessThanRequired() {
        assertFalse(testingService.isTestPassed(pointsToPass - 1));
    }

    @Test
    void shouldPassTestIfCorrectAnswersEnough() {
        assertTrue(testingService.isTestPassed(pointsToPass));
    }
}
