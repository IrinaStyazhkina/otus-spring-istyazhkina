package ru.otus.istyazhkina.testapp.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.istyazhkina.testapp.domain.Student;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StudentServiceImplTests {

    @Autowired
    private StudentService studentService;

    @MockBean
    private IOService ioService;

    @Test
    void shouldGetCorrectStudentData() {
        Mockito.when(ioService.read())
                .thenReturn("Ivan")
                .thenReturn("Petrov");
        Student student = studentService.getStudentData();
        assertThat(student.getName()).isEqualTo("Ivan");
        assertThat(student.getSurname()).isEqualTo("Petrov");
    }
}
