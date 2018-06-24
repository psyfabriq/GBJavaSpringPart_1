/*
package ru.podstavkov;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ru.podstavkov.config.AppConfig;
import ru.podstavkov.entity.Category;
import ru.podstavkov.entity.Company;
import ru.podstavkov.service.AplicationService;
import ru.podstavkov.utils.AppUtil;


public class TestContextAnnotation {
	
	private ObjectMapper mapper = new ObjectMapper();
	private ArrayNode arrayNode = mapper.createArrayNode(); 
	

	
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			//throw new IllegalArgumentException("max must be greater than min");
			return 0;
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	
	  @Test
	    private void test() {
	        final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	        
	        final AplicationService tservice = context.getBean("appservice", AplicationService.class);
	        
	        List<Company> lcompany = new ArrayList<>();
	        List<Category> lcategory = new ArrayList<>();
	        /*
	        for (int i = 0; i < 3; i++) {
		        ObjectNode objectNodeCategory = mapper.createObjectNode();
		        objectNodeCategory.put("name", "Category"+i);
		        Category c = tservice.createCategory(AppUtil.getValues(objectNodeCategory.toString()));
		        lcategory.add(c);
		        Assert.assertNotNull(c);
			}
	        */
	        /*
	        for (int i = 0; i < 4; i++) {
		        ObjectNode objectNodeCompany = mapper.createObjectNode();
		        objectNodeCompany.put("name", "COMPANY"+i);
		        objectNodeCompany.put("address", "ADRESS");
		        objectNodeCompany.put("description", "DESCRIPTION");
		        Company c = tservice.createCompany(AppUtil.getValues(objectNodeCompany.toString()));
		        lcompany.add(c);
		        Assert.assertNotNull(c);
	        }
	       */
	        /*
	        lcompany = (List<Company>)tservice.listCompany();
	        lcategory = (List<Category>)tservice.listCategory();
	        
	        for (int i = 0; i < 7; i++) {
	        	ObjectNode objectNodeTask  = mapper.createObjectNode();
	        	
	        	Company com = lcompany.get(getRandomNumberInRange(1,lcompany.size())-1);

		        objectNodeTask.put("name", "task"+ new Date().toString());
		        objectNodeTask.put("content", "some text : ))))))");
		        objectNodeTask.put("owner_id", com.getId());
		        
		        ArrayNode arrayCategoriesID = objectNodeTask.putArray("category_id");
		        
		 	       for (int k = 1; k <= getRandomNumberInRange(1,lcategory.size()); k++) {
		 	    	   Category c = lcategory.get(getRandomNumberInRange(1,lcategory.size())-1);
		 	    	   System.out.println("I "+i+" k "+ k + " value " + c.getId());
				       arrayCategoriesID.add(c.getId());
		 	       }
		        
		        Assert.assertNotNull(tservice.createTask(AppUtil.getValues(objectNodeTask.toString())));
  
	        }
	        

	        
	        
	    }
}
*/
