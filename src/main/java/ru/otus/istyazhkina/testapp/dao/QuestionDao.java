package ru.otus.istyazhkina.testapp.dao;

import ru.otus.istyazhkina.testapp.domain.Question;
import ru.otus.istyazhkina.testapp.exceptions.QuestionReadException;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions() throws QuestionReadException;
}
