package ru.otus.istyazhkina.service;

import ru.otus.istyazhkina.data.Question;

import java.util.List;

public class TestingServiceImpl implements TestingService {
    private final IOService ioService;
    private final List<Question> questionList;

    public TestingServiceImpl(IOService ioService, QuestionService questionService) {
        this.ioService = ioService;
        this.questionList = questionService.getQuestions();
    }

    public void runTest() {
        ioService.write("Welcome to the testing platform! Here are 5 questions with answers, you have to choose correct one");

        for (Question question : questionList) {
            ioService.write("Question: " + question.getQuestion());

            ioService.write("Answers:");
            List<String> answers = question.getAnswers();
            for (String answer : answers) {
                ioService.write(answer);
            }
            ioService.write("--------");
        }
        ioService.write("End of question list");
    }
}
