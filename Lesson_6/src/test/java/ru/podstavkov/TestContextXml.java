package ru.podstavkov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class TestContextXml {
	  @Test
	    private void test() {
	        final ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");

	        
	    }
}
