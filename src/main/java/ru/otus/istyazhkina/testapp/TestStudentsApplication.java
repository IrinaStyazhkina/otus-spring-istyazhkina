package ru.otus.istyazhkina.testapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.istyazhkina.testapp.service.AppRunnerService;

import java.util.Locale;

@SpringBootApplication
public class TestStudentsApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(TestStudentsApplication.class, args);
		Locale.setDefault(Locale.forLanguageTag("ru-RU"));
		ctx.getBean(AppRunnerService.class).runTest();
	}
}
