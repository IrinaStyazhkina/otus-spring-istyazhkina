package ru.otus.istyazhkina.testapp.service;

import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.testapp.domain.Student;

@Service
public class AppRunnerServiceImpl implements AppRunnerService {

    private final TestingService testingService;
    private final StudentService studentService;
    private final LanguageService languageService;

    public AppRunnerServiceImpl(TestingService testingService, StudentService studentService, LanguageService languageService) {
        this.testingService = testingService;
        this.studentService = studentService;
        this.languageService = languageService;
    }


    @Override
    public void runTest() {
        languageService.chooseAppLanguage();
        final Student studentData = studentService.getStudentData();
        testingService.askQuestions(studentData);
    }
}
