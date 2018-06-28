/*
package ru.podstavkov.test;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ru.podstavkov.entity.Category;
import ru.podstavkov.entity.Company;
import ru.podstavkov.entity.Task;
import ru.podstavkov.service.AppService;
import ru.podstavkov.service.CategoryService;
import ru.podstavkov.service.CompanyService;
import ru.podstavkov.service.TaskService;
import ru.podstavkov.test.config.AppTestConfig;
import ru.podstavkov.utils.AppUtil;

public class TestContextAnnotation {
	
	private ObjectMapper mapper = new ObjectMapper();
	private ArrayNode arrayNode = mapper.createArrayNode();

	final ApplicationContext context = new AnnotationConfigApplicationContext(AppTestConfig.class);
	
	final AppService<Task>     taskService   	= context.getBean("taskservice", TaskService.class);
	final AppService<Company>  companyService  	= context.getBean("companyservice", CompanyService.class);
	final AppService<Category> categoryService  = context.getBean("categoryservice", CategoryService.class);
     
	private List<Company> lcompany;
	private List<Category> lcategory;
	private List<Task> ltask ;

	private static int getRandomNumberInRange(int min, int max) {
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		return randomNum;
	}

	private void addCompanies(int count) {
		for (int i = 0; i < count; i++) {
			ObjectNode objectNodeCompany = mapper.createObjectNode();
			objectNodeCompany.put("name", "COMPANY " + i);
			objectNodeCompany.put("address", "ADRESS  " + i);
			objectNodeCompany.put("description", "DESCRIPTION " + i);
			Company c = companyService.create(AppUtil.getValues(objectNodeCompany.toString()));
			Assert.assertNotNull(c);
		}
	}

	private void addCategories(int count) {
		for (int i = 0; i < count; i++) {
			ObjectNode objectNodeCategory = mapper.createObjectNode();
			objectNodeCategory.put("name", "Category " + i);
			Category c = categoryService.create(AppUtil.getValues(objectNodeCategory.toString()));
			Assert.assertNotNull(c);
		}

	}

	private void addTasks(int count) {

		lcompany  = companyService.getList();
		lcategory = categoryService.getList();
	    ltask     = taskService.getList();

		for (int i = ltask.size(); i < ltask.size()+count; i++) {
			ObjectNode objectNodeTask = mapper.createObjectNode();
			Company com = lcompany.get(getRandomNumberInRange(0, lcompany.size()- 1));
			
			objectNodeTask.put("name", "task " + i);
			objectNodeTask.put("content", "some text : ))))))");
			objectNodeTask.put("owner_id", com.getId());

			ArrayNode arrayCategoriesID = objectNodeTask.putArray("category_id");

			for (int k = 1; k <= getRandomNumberInRange(1, lcategory.size()); k++) {
				Category c = lcategory.get(getRandomNumberInRange(0, lcategory.size()- 1));
				System.out.println("I " + i + " k " + k + " value " + c.getId());
				arrayCategoriesID.add(c.getId());
			}
			Assert.assertNotNull(taskService.create(AppUtil.getValues(objectNodeTask.toString())));
		}
	}
	


	@Test
	private void test() {
	}

}
*/
