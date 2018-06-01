package ru.podstavkov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import ru.podstavkov.AppConfig;
import ru.podstavkov.beans.Gun;
import ru.podstavkov.beans.GunImpl;


public class TestContextAnnotation {
	
	  @Test
	    private void test() {
	        final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	        
	        final Gun gun = context.getBean("my_gun", Gun.class);
	        System.out.println(gun.hashCode());
	        Assert.assertNotNull(gun);
	        
	    }
}
