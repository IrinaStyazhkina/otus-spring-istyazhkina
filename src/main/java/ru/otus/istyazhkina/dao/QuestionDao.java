package ru.otus.istyazhkina.dao;

import ru.otus.istyazhkina.data.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionDao {

    List<Question> getQuestions() throws IOException;

}
