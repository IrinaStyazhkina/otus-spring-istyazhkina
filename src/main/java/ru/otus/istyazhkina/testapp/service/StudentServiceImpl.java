package ru.otus.istyazhkina.testapp.service;

import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.testapp.domain.Student;

@Service
public class StudentServiceImpl implements StudentService {

    private final IOService ioService;
    private final PrintTranslatedMessagesFacade printTranslatedMessagesFacade;

    public StudentServiceImpl(IOService ioService, PrintTranslatedMessagesFacade printTranslatedMessagesFacade) {
        this.ioService = ioService;
        this.printTranslatedMessagesFacade = printTranslatedMessagesFacade;
    }

    @Override
    public Student getStudentData() {
        printTranslatedMessagesFacade.printTranslated("app.student.greeting");
        printTranslatedMessagesFacade.printTranslated("app.student.ask.name");
        String name = ioService.read();
        printTranslatedMessagesFacade.printTranslated("app.student.ask.surname");
        String surname = ioService.read();
        Student student = new Student(name, surname);

        printTranslatedMessagesFacade.printTranslated("app.student.testing.introduction", student.getName(), student.getSurname());
        return student;
    }
}
