package ru.otus.istyazhkina.testapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestingServiceImplTests {

    @Value("${application.points-to-pass}")
    private int pointsToPass;

    @Autowired
    TestingServiceImpl testingService;

    @Test
    void shouldFailTestIfCorrectAnswersLessThanRequired() {
        String isTestPassed = testingService.isTestPassed(pointsToPass - 1);
        assertThat(isTestPassed).isEqualTo(String.format("Oops! Test not passed! Count of your correct answers is: %s, but to pass test %s correct answers required!", pointsToPass - 1, pointsToPass));
    }

    @Test
    void shouldPassTestIfCorrectAnswersEnough() {
        String isTestPassed = testingService.isTestPassed(pointsToPass);
        assertThat(isTestPassed).isEqualTo(String.format("Congratulations! You have passed the test! You had %s correct answers!", pointsToPass));
    }
}
