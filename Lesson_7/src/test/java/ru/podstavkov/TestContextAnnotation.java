/*
package ru.podstavkov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ru.podstavkov.config.AppConfig;
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
	        objectNodeCategory.put("name", "Test Category 2");
	        
	       //Assert.assertNotNull(tservice.createCategory(AppUtil.getValues(objectNodeCategory.toString())));
	        
	        ObjectNode objectNodeCompany = mapper.createObjectNode();
	        objectNodeCompany.put("name", "BADS COMPANY");
	        objectNodeCompany.put("address", "GERMANY");
	        objectNodeCompany.put("description", "big company ))))");
	        
	       // Assert.assertNotNull(tservice.createCompany(AppUtil.getValues(objectNodeCompany.toString())));
	        
	        ObjectNode objectNodeTask  = mapper.createObjectNode();

	        objectNodeTask.put("name", "task 3G");
	        objectNodeTask.put("content", "some text : ))))))");
	        objectNodeTask.put("owner_id", "e21e2b27-bb08-4e72-8dc8-d3f700082dcc");
	        
	        ArrayNode arrayCategoriesID = objectNodeTask.putArray("category_id");
	        //arrayCategoriesID.add("2632517a-61b6-4d6d-bc9d-19628e539f05");
	        arrayCategoriesID.add("866983ae-5b1b-4004-a75c-d7f9556bf27f");
	        

	        Assert.assertNotNull(tservice.createTask(AppUtil.getValues(objectNodeTask.toString())));
	        
	       // ObjectNode objectNodeCompanyGet = mapper.createObjectNode();
	        
	       // Assert.assertNotNull(tservice.getCompany("040536d2-c16c-40bd-b02c-38ab25501ce7").getTasks());
	        
	       // for (Task task : tservice.getCompany("040536d2-c16c-40bd-b02c-38ab25501ce7").getTasks()) {
			//	System.out.println(task.getName());
			//}
	        
	        //Assert.assertTrue(tservice.deleteTask("d384b336-54da-4bb5-9aac-0804fd7ab90d"));
	    }
}
*/