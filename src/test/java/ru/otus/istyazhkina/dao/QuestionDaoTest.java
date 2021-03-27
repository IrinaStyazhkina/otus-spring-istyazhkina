package ru.otus.istyazhkina.dao;

import org.junit.jupiter.api.Test;
import ru.otus.istyazhkina.data.Question;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionDaoTest {

    @Test
    void questionsFileShouldHaveFiveQuestions() throws IOException {
        QuestionDao questionDao = new QuestionDaoImpl("questions.csv");
        List<Question> questions = questionDao.getQuestions();
        assertEquals(5, questions.size());
    }
}
