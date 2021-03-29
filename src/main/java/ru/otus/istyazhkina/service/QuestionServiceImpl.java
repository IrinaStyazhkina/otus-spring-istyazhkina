package ru.otus.istyazhkina.service;

import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.dao.QuestionDao;
import ru.otus.istyazhkina.domain.Question;
import ru.otus.istyazhkina.exception.ReadCsvException;

import java.io.IOException;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> getQuestions() {
        try {
            return questionDao.getQuestions();
        } catch (ReadCsvException e) {
            e.printStackTrace();
        }
        return null;
    }
}
