package ru.otus.istyazhkina.testapp.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.istyazhkina.testapp.service.IOService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ApplicationCommandsTests {

    @Autowired
    private Shell shell;

    @MockBean
    IOService ioService;

    @DisplayName("Нельзя вызвать команду test до вызвоа команды greet")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void shouldNotAllowRunTestingWithoutGreeting() {
        Object res = shell.evaluate(() -> "test");
        assertThat(res).isInstanceOf(CommandNotCurrentlyAvailable.class);
    }

    @DisplayName("Вызов команды greet завершается приветствием студента")
    @Test
    void shouldReturnGreetingForGreetCommand() {
        Mockito.when(ioService.read())
                .thenReturn("Ivan")
                .thenReturn("Ivanov");
        Object res = shell.evaluate(() -> "greet");
        assertEquals("Dear Ivan Ivanov. You will be asked five questions. After each question please choose the right answer and write down the number of chosen variant!", res);
    }
}
