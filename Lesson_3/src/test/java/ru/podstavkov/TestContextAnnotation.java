package ru.podstavkov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;


public class TestContextAnnotation {
	
	  @Test
	    private void test() {
	        final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	        
	    }
}
