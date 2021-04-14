package ru.otus.istyazhkina.testapp.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.istyazhkina.testapp.domain.Student;
import ru.otus.istyazhkina.testapp.service.LanguageService;
import ru.otus.istyazhkina.testapp.service.LocalizationService;
import ru.otus.istyazhkina.testapp.service.StudentService;
import ru.otus.istyazhkina.testapp.service.TestingService;

@ShellComponent
public class ApplicationCommands {

    private final LanguageService languageService;
    private final TestingService testingService;
    private final StudentService studentService;
    private final LocalizationService localizationService;
    private Student student;

    public ApplicationCommands(LanguageService languageService, TestingService testingService, StudentService studentService, LocalizationService localizationService) {
        this.languageService = languageService;
        this.testingService = testingService;
        this.studentService = studentService;
        this.localizationService = localizationService;
    }

    @ShellMethod(value = "Choose Language", key = {"lang"})
    public void chooseLanguage() {
        languageService.chooseAppLanguage();
    }

    @ShellMethod(value = "Get Students Name and Surname", key = {"greet"})
    public String getStudentData() {
        student = studentService.getStudentData();
        return localizationService.getMessageByKey("app.student.testing.introduction", student.getName(), student.getSurname());
    }

    @ShellMethod(value = "Run Tests", key = {"test"})
    @ShellMethodAvailability(value = "isTestingAvailable")
    public void doTest() {
        testingService.askQuestions(student);
    }


    private Availability isTestingAvailable() {
        return student == null ? Availability.unavailable(localizationService.getMessageByKey("app.shell.greet.required")) : Availability.available();
    }

}
