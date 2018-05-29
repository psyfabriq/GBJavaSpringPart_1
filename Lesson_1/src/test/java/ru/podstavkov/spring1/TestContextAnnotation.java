package ru.podstavkov.spring1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import ru.podstavkov.spring1.beans.Gun;


public class TestContextAnnotation {
	
	  @Test
	    private void test() {
	        final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	        
	        final Gun gun = context.getBean("Gun", Gun.class);
	        System.out.println(gun.hashCode());
	        Assert.assertNotNull(gun);
	        Assert.assertNotNull(gun.getBullet()); 
	        // Тест будет провален так как не находит класс патронов
	    }
}
