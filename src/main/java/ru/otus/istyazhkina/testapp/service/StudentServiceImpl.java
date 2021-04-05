package ru.otus.istyazhkina.testapp.service;

import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.testapp.domain.Student;

@Service
public class StudentServiceImpl implements StudentService {

    private final IOService ioService;
    private final LocalizationService localizationService;

    public StudentServiceImpl(IOService ioService, LocalizationService localizationService) {
        this.ioService = ioService;
        this.localizationService = localizationService;
    }

    @Override
    public Student getStudentData() {
        ioService.write(localizationService.getMessageByKey("app.student.greeting"));
        ioService.write(localizationService.getMessageByKey("app.student.ask.name"));
        String name = ioService.read();
        ioService.write(localizationService.getMessageByKey("app.student.ask.surname"));
        String surname = ioService.read();
        Student student = new Student(name, surname);

        ioService.write(localizationService.getMessageByKey("app.student.testing.introduction", student.getName(), student.getSurname()));
        return student;
    }
}
