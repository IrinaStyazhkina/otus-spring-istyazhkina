package ru.otus.istyazhkina.testapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.istyazhkina.testapp.service.AppRunnerService;

@SpringBootApplication
public class TestStudentsApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(TestStudentsApplication.class, args);
		ctx.getBean(AppRunnerService.class).runTest();

	}
}
