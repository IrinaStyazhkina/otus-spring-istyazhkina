package ru.otus.istyazhkina.testapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.testapp.domain.Question;
import ru.otus.istyazhkina.testapp.domain.Student;

import java.util.List;

@Service
public class TestingServiceImpl implements TestingService {
    private final IOService ioService;
    private final PrintTranslatedMessagesFacade printTranslatedMessagesFacade;
    private final QuestionService questionService;
    private final Integer pointsToPassTest;

    public TestingServiceImpl(IOService ioService, QuestionService questionService, @Value("${application.points-to-pass}") Integer pointsToPassTest, PrintTranslatedMessagesFacade printTranslatedMessagesFacade
    ) {
        this.ioService = ioService;
        this.questionService = questionService;
        this.pointsToPassTest = pointsToPassTest;
        this.printTranslatedMessagesFacade = printTranslatedMessagesFacade;
    }

    @Override
    public void askQuestions(Student student) {
        final List<Question> questionList = questionService.getQuestions();
        int correctAnswers = 0;
        for (Question question : questionList) {
            askQuestion(question);
            if (checkAnswer(question)) {
                correctAnswers++;
            }
        }
        ioService.write("--------------");
        if (isTestPassed(correctAnswers)) {
            printTranslatedMessagesFacade.printTranslated("app.testing.passed", student.getName(), student.getSurname(), correctAnswers);
        } else {
            printTranslatedMessagesFacade.printTranslated("app.testing.failed", student.getName(), student.getSurname(), correctAnswers, pointsToPassTest);
        }
    }

    void askQuestion(Question question) {
        printTranslatedMessagesFacade.printTranslated("app.testing.question");
        ioService.write(question.getQuestion());

        printTranslatedMessagesFacade.printTranslated("app.testing.answers");
        List<String> answers = question.getAnswers();
        for (String answer : answers) {
            ioService.write(answer);
        }
    }

    boolean checkAnswer(Question question) {
        printTranslatedMessagesFacade.printTranslated("app.testing.student.answer");
        final int studentAnswer = ioService.readIntInRange(1, 3);
        return question.getCorrectAnswer() == studentAnswer;
    }

    boolean isTestPassed(int correctAnswers) {
        return correctAnswers >= pointsToPassTest;
    }
}
