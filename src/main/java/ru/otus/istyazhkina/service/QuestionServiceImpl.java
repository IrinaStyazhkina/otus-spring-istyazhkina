package ru.otus.istyazhkina.service;

import ru.otus.istyazhkina.dao.QuestionDao;
import ru.otus.istyazhkina.data.Question;

import java.io.IOException;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> getQuestions() {
        try {
            return questionDao.getQuestions();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
