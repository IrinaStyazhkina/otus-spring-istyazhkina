package ru.otus.istyazhkina.testapp.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.istyazhkina.testapp.domain.Question;
import ru.otus.istyazhkina.testapp.service.LocalizationService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QuestionDaoImplTests {

    @Autowired
    private LocalizationService localizationService;

    @Test
    void questionsFileShouldHaveFiveQuestions() {
        QuestionDao questionDao = new QuestionDaoImpl(localizationService);
        List<Question> questions = questionDao.getQuestions();
        assertEquals(5, questions.size());
    }

    @Test
    void questionsParseTest() {
        QuestionDao questionDao = new QuestionDaoImpl(localizationService);
        List<Question> questions = questionDao.getQuestions();
        Question question = questions.get(0);
        assertThat(question.getQuestion()).isEqualTo("How to declare a class in Java?");
        assertThat(question.getAnswers().get(0)).isEqualTo("1. new class MyClass {}");
        assertThat(question.getAnswers().get(1)).isEqualTo("2. class MyClass {}");
        assertThat(question.getAnswers().get(2)).isEqualTo("3. select * from class MyClass {}");
        assertThat(question.getCorrectAnswer()).isEqualTo(2);
    }
}
