package ru.podstavkov;

import java.util.Collection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.podstavkov.configuration.AppConfig;
import ru.podstavkov.dao.TaskDAO;
import ru.podstavkov.entity.Task;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		TaskDAO articleRepository = context.getBean("adDAO", TaskDAO.class);
		
		Collection<Task> tasks = articleRepository.getListTask();
		tasks.forEach(ad -> {
			System.out.println("Category: " + ad.getCategory());
		});

	}

}
