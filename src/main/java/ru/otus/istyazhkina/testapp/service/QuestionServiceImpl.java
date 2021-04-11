package ru.otus.istyazhkina.testapp.service;

import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.testapp.aspect.logging.Logger;
import ru.otus.istyazhkina.testapp.dao.QuestionDao;
import ru.otus.istyazhkina.testapp.domain.Question;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    @Logger
    public List<Question> getQuestions() {
        return questionDao.getQuestions();
    }
}
