package ru.otus.istyazhkina;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.istyazhkina.service.TestingService;

public class TestingApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        TestingService testingService = context.getBean(TestingService.class);
        testingService.runTest();
    }
}
