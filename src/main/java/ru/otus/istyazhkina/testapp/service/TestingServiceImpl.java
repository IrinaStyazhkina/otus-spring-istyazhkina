package ru.otus.istyazhkina.testapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.testapp.domain.Question;

import java.util.List;

@Service
public class TestingServiceImpl implements TestingService {
    private final IOService ioService;
    private final QuestionService questionService;
    private final LocalizationService localizationService;
    private final Integer pointsToPassTest;

    public TestingServiceImpl(IOService ioService, QuestionService questionService, LocalizationService localizationService, @Value("${application.points-to-pass}") Integer pointsToPassTest) {
        this.ioService = ioService;
        this.questionService = questionService;
        this.localizationService = localizationService;
        this.pointsToPassTest = pointsToPassTest;
    }

    @Override
    public void askQuestions() {
        final List<Question> questionList = questionService.getQuestions();
        int correctAnswers = 0;
        for (Question question : questionList) {
            askQuestion(question);
            if (checkAnswer(question)) {
                correctAnswers++;
            }
        }
        ioService.write("--------------");
        ioService.write(isTestPassed(correctAnswers));
    }

    void askQuestion(Question question) {
        ioService.write(localizationService.getMessageByKey("app.testing.question") + " " + question.getQuestion());

        ioService.write(localizationService.getMessageByKey("app.testing.answers"));
        List<String> answers = question.getAnswers();
        for (String answer : answers) {
            ioService.write(answer);
        }
    }

    boolean checkAnswer(Question question) {
        ioService.write(localizationService.getMessageByKey("app.testing.student.answer"));
        final int studentAnswer = ioService.readIntInRange(1, 3);
        return question.getCorrectAnswer() == studentAnswer;
    }

    String isTestPassed(int correctAnswers) {
        if (correctAnswers < pointsToPassTest) {
            return localizationService.getMessageByKey("app.testing.failed", correctAnswers, pointsToPassTest);
        } else {
            return localizationService.getMessageByKey("app.testing.passed", correctAnswers);
        }
    }
}
