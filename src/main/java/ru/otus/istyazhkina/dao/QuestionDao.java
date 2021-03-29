package ru.otus.istyazhkina.dao;

import ru.otus.istyazhkina.domain.Question;
import ru.otus.istyazhkina.exception.ReadCsvException;

import java.io.IOException;
import java.util.List;

public interface QuestionDao {

    List<Question> getQuestions() throws ReadCsvException;


}
