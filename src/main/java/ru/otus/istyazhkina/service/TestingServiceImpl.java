package ru.otus.istyazhkina.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.domain.Question;
import ru.otus.istyazhkina.domain.Student;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

@Service
public class TestingServiceImpl implements TestingService {
    private final IOService ioService;
    private final QuestionService questionService;
    private final Integer pointsToPassTest;

    public TestingServiceImpl(IOService ioService, QuestionService questionService, @Value("${pointsToPass}") Integer pointsToPassTest) {
        this.ioService = ioService;
        this.questionService = questionService;
        this.pointsToPassTest = pointsToPassTest;
    }

    @Override
    public int askQuestions() {
        final List<Question> questionList = questionService.getQuestions();
        int correctAnswers  = 0;
        for (Question question : questionList) {
            askQuestion(question);
            final int answer = readAnswer();
            if(checkAnswer(question, answer)) {
                correctAnswers++;
            }
        }
        ioService.write("--------------");
        return correctAnswers;
    }


    private void askQuestion(Question question) {
        ioService.write("Question: " + question.getQuestion());

        ioService.write("Answers:");
        List<String> answers = question.getAnswers();
        for (String answer : answers) {
            ioService.write(answer);
        }

    }

    private int readAnswer() {
        ioService.write("Your answer is:");
        int answer = 0;
        do {
            try {
                int input = Integer.parseInt(ioService.read());
                if(input < 0 || input > 3) {
                    throw new InputMismatchException("Available answers are: 1, 2 or 3");
                }
                answer = input;
            }
            catch (Exception e) {
                ioService.write("Invalid data. Please write down the number of chosen answer!");
            }
        }
        while (answer == 0);
        return answer;
    }

    private boolean checkAnswer(Question question, int studentsAnswer) {
        return question.getCorrectAnswer() == studentsAnswer;
    }

    @Override
    public void countTestResults(int correctAnswers) {
        if(correctAnswers < pointsToPassTest) {
            ioService.write(String.format("Oops! Test not passed! Count of your correct answers is: %s, but to pass test %s correct answers required!", correctAnswers, pointsToPassTest));
        }
        else {
            ioService.write(String.format("Congratulations! You have passed the test! You had %s correct answers!", correctAnswers));
        }
    }
}
