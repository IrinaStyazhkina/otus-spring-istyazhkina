package ru.otus.istyazhkina.dao;

import org.junit.jupiter.api.Test;
import ru.otus.istyazhkina.domain.Question;
import ru.otus.istyazhkina.exception.ReadCsvException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuestionDaoTest {

    @Test
    void questionsFileShouldHaveFiveQuestions() {
        QuestionDao questionDao = new QuestionDaoImpl("questions.csv");
        List<Question> questions = questionDao.getQuestions();
        assertEquals(5, questions.size());
    }

    @Test
    void shouldThrowReadCsvExceptionForIncorrectCsvFile() {
        QuestionDao questionDao = new QuestionDaoImpl("incorrect_questions.csv");
        ReadCsvException readCsvException = assertThrows(ReadCsvException.class, questionDao::getQuestions);
        assertEquals("Incorrect csv file. Check that sequence of questions data is correct!", readCsvException.getMessage());
    }

    @Test
    void shouldThrowReadCsvExceptionIfCsvFileNotExists() {
        QuestionDao questionDao = new QuestionDaoImpl("no_data.csv");
        ReadCsvException readCsvException = assertThrows(ReadCsvException.class, questionDao::getQuestions);
        assertEquals("No such csv file. Check the path", readCsvException.getMessage());
    }


}
