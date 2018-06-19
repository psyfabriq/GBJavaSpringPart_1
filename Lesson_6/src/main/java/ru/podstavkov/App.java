package ru.podstavkov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.podstavkov.config.AppConfig;

public class App {
	public static void main(String[] args) {
        final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	}

}
