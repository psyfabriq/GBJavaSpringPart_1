package ru.podstavkov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ru.podstavkov.configuration.AppConfig;
import ru.podstavkov.service.AplicationService;
import ru.podstavkov.utils.AppUtil;


public class TestContextAnnotation {
	
	private ObjectMapper mapper = new ObjectMapper();
	private ArrayNode arrayNode = mapper.createArrayNode(); 
	
	  @Test
	    private void test() {
	        final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	        
	        final AplicationService tservice = context.getBean("appservice", AplicationService.class);
	        
	        ObjectNode objectNodeCategory = mapper.createObjectNode();
	        objectNodeCategory.put("name", "Test Category 1");
	        
	      //  Assert.assertTrue(tservice.createCategory(AppUtil.getValues(objectNodeCategory.toString())));
	        
	        ObjectNode objectNodeCompany = mapper.createObjectNode();
	        objectNodeCompany.put("name", "GOODS COMPANY");
	        objectNodeCompany.put("address", "LONDON");
	        objectNodeCompany.put("description", "litle company ))))");
	        
	      //  Assert.assertTrue(tservice.createCompany(AppUtil.getValues(objectNodeCompany.toString())));
	    }
}
