package ru.otus.istyazhkina.service;

import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.domain.Student;

@Service
public class AppRunnerServiceImpl implements AppRunnerService {

    private final IOService ioService;
    private final TestingService testingService;

    public AppRunnerServiceImpl(IOService ioService, TestingService testingService) {
        this.ioService = ioService;
        this.testingService = testingService;
    }

    @Override
    public void runTest() {
        ioService.write("Welcome to the testing platform!");
        ioService.write("What is your name?");
        String name = ioService.read();
        ioService.write("What is your surname?");
        String surname = ioService.read();
        Student student = new Student(name, surname);

        ioService.write(String.format("Dear %s %s. You will be asked five questions. After each question please choose " +
                "the right answer and write down the number of chosen variant!", student.getName(), student.getSurname()));

        final int correctAnswers = testingService.askQuestions();
        testingService.countTestResults(correctAnswers);
    }
}
