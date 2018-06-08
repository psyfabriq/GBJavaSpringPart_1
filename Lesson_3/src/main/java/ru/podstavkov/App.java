package ru.podstavkov;

import java.util.Collection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.podstavkov.dao.AdDAO;
import ru.podstavkov.entity.Ad;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		AdDAO articleRepository = context.getBean("adDAO", AdDAO.class);
		
		Collection<Ad> ads = articleRepository.getListAd();
		ads.forEach(ad -> {
			System.out.println("Category: " + ad.getCategory());
		});

	}

}
