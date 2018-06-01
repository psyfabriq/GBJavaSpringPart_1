package ru.podstavkov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import ru.podstavkov.beans.GunImpl;

public class TestContextXml {
	  @Test
	    private void test() {
	        final ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
	     ///   System.out.println(context.getBean("beean").hashCode());
	        
	        final GunImpl gun = context.getBean("Gun2", GunImpl.class);
	        System.out.println(gun.hashCode());
	        Assert.assertNotNull(gun);
	        Assert.assertNotNull(gun.getBullet()); 
	        
	    }
}
