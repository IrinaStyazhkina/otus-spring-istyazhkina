package ru.otus.istyazhkina;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.istyazhkina.service.AppRunnerService;
import ru.otus.istyazhkina.service.TestingService;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class TestingApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestingApplication.class);
        AppRunnerService appRunnerService = context.getBean(AppRunnerService.class);
        appRunnerService.runTest();
    }
}
