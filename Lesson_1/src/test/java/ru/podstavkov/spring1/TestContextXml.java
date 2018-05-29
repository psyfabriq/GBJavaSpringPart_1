package ru.podstavkov.spring1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import ru.podstavkov.spring1.beans.Gun;

public class TestContextXml {
	  @Test
	    private void test() {
	        final ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
	     ///   System.out.println(context.getBean("beean").hashCode());
	        
	        final Gun gun = context.getBean("Gun2", Gun.class);
	        System.out.println(gun.hashCode());
	        Assert.assertNotNull(gun);
	        Assert.assertNotNull(gun.getBullet()); 
	        
	    }
}
