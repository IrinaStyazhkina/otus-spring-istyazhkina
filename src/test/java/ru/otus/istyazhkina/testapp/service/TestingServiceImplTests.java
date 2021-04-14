package ru.otus.istyazhkina.testapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.istyazhkina.testapp.domain.Question;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TestingServiceImplTests {

    @Value("${application.points-to-pass}")
    private int pointsToPass;

    @Autowired
    TestingServiceImpl testingService;

    @MockBean
    QuestionService questionServiceMock;

    @MockBean
    IOService ioServiceMock;

    @BeforeEach
    void setUp() {
        List<Question> questions = Collections.singletonList(new Question("Question", Arrays.asList("1. answ1", "2. answ2", "3. answ3"), 1));
        Mockito.when(questionServiceMock.getQuestions()).thenReturn(questions);
    }

    @Test
    void shouldFailTestIfCorrectAnswersLessThanRequired() {
        assertFalse(testingService.isTestPassed(pointsToPass - 1));
    }

    @Test
    void shouldPassTestIfCorrectAnswersEnough() {
        assertTrue(testingService.isTestPassed(pointsToPass));
    }

    @Test
    void shouldAnswerCheckPassIfAnswerIsCorrect() {
        Mockito.when(ioServiceMock.readIntInRange(1, 3)).thenReturn(1);
        assertTrue(testingService.checkAnswer(questionServiceMock.getQuestions().get(0)));
    }

    @Test
    void shouldAnswerCheckFailIfAnswerIsIncorrect() {
        Mockito.when(ioServiceMock.readIntInRange(1, 3)).thenReturn(2);
        assertFalse(testingService.checkAnswer(questionServiceMock.getQuestions().get(0)));
    }
}
