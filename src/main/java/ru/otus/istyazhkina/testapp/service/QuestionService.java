package ru.otus.istyazhkina.testapp.service;

import ru.otus.istyazhkina.testapp.domain.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getQuestions();
}
