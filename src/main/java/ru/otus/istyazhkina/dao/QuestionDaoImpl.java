package ru.otus.istyazhkina.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.istyazhkina.domain.Question;
import ru.otus.istyazhkina.exception.ReadCsvException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Repository
public class QuestionDaoImpl implements QuestionDao {
    private final String resource;

    public QuestionDaoImpl(@Value("${questionsFile}") String resource) {
        this.resource = resource;
    }

    public List<Question> getQuestions() throws ReadCsvException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resource);
             Scanner sc = new Scanner(inputStream)) {
            List<Question> questions = new ArrayList<>();
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] splitted = line.split(";");
                Question question = new Question(splitted[0], Arrays.asList(splitted[1], splitted[2], splitted[3]), Integer.parseInt(splitted[4]));
                questions.add(question);
            }
            return questions;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new ReadCsvException("Incorrect csv file. Check that sequence of questions data is correct!", e);
        }
        catch (NullPointerException e) {
            throw new ReadCsvException("No such csv file. Check the path", e);
        }
        catch (IOException e) {
            throw new ReadCsvException("Error while reading csv file with questions", e);
        }
    }
}
