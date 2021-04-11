package ru.otus.istyazhkina.testapp.dao;

import org.springframework.stereotype.Repository;
import ru.otus.istyazhkina.testapp.config.LanguageConfig;
import ru.otus.istyazhkina.testapp.domain.Question;
import ru.otus.istyazhkina.testapp.exceptions.QuestionReadException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;

@Repository
public class QuestionDaoImpl implements QuestionDao {
    private final LanguageConfig languageConfig;

    public QuestionDaoImpl(LanguageConfig languageConfig) {
        this.languageConfig = languageConfig;
    }

    public List<Question> getQuestions() throws QuestionReadException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(languageConfig.getQuestionsFile());
             Scanner sc = new Scanner(requireNonNull(inputStream))) {
            List<Question> questions = new ArrayList<>();
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] splitted = line.split(";");
                Question question = new Question(splitted[0], Arrays.asList(splitted[1], splitted[2], splitted[3]), Integer.parseInt(splitted[4]));
                questions.add(question);
            }
            return questions;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new QuestionReadException("Incorrect csv file. Check that sequence of questions data is correct!", e);
        } catch (IOException e) {
            throw new QuestionReadException("Error while reading csv file with questions", e);
        }
    }
}
