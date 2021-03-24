package ru.otus.istyazhkina.dao;

import ru.otus.istyazhkina.data.Question;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class QuestionDaoImpl implements QuestionDao {
    private final String resource;

    public QuestionDaoImpl(String resource) {
        this.resource = resource;
    }

    public List<Question> getQuestions() throws IOException {
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
    }
}
