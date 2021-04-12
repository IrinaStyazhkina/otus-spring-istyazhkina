package ru.otus.istyazhkina.testapp.dao;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.istyazhkina.testapp.config.LanguageConfig;
import ru.otus.istyazhkina.testapp.domain.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;

@SpringBootTest()
public class QuestionDaoImplTests {

    @Autowired
    private QuestionDao questionDao;

    @Configuration
    static class TestConfiguration {

        @Bean
        QuestionDao questionDao() {
            LanguageConfig languageConfigMock = Mockito.mock(LanguageConfig.class);
            doAnswer(invocation -> "questions/questions_en_EN.csv").when(languageConfigMock).getQuestionsFile();
            return new QuestionDaoImpl(languageConfigMock);
        }
    }

    @Test
    void questionsFileShouldHaveFiveQuestions() {
        List<Question> questions = questionDao.getQuestions();
        assertEquals(5, questions.size());
    }

    @Test
    void questionsParseTest() {
        List<Question> questions = questionDao.getQuestions();
        Question question = questions.get(0);
        assertThat(question.getQuestion()).isEqualTo("How to declare a class in Java?");
        assertThat(question.getAnswers().get(0)).isEqualTo("1. new class MyClass {}");
        assertThat(question.getAnswers().get(1)).isEqualTo("2. class MyClass {}");
        assertThat(question.getAnswers().get(2)).isEqualTo("3. select * from class MyClass {}");
        assertThat(question.getCorrectAnswer()).isEqualTo(2);
    }
}
