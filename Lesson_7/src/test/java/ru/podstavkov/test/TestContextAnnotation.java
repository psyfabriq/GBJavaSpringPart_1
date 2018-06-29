
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
	
	private void start() {
		lcompany  = companyService.getList();
		lcategory = categoryService.getList();
	    ltask     = taskService.getList();
	}

	private void addCompanies(int count) {
		int size = lcompany.size();
		System.out.println(size);
	
		for (int i = size; i < size+count; i++) {
			ObjectNode objectNodeCompany = mapper.createObjectNode();
			objectNodeCompany.put("name", "COMPANY " + i);
			objectNodeCompany.put("address", "ADRESS  " + i);
			objectNodeCompany.put("description", "DESCRIPTION " + i);
			Company company = companyService.create(AppUtil.getValues(objectNodeCompany.toString()));
			lcompany.add(company);
			Assert.assertNotNull(company);
		}
		
	}

	private void addCategories(int count) {
		for (int i = lcategory.size(); i < lcategory.size()+count; i++) {
			ObjectNode objectNodeCategory = mapper.createObjectNode();
			objectNodeCategory.put("name", "Category " + i);
			Category category = categoryService.create(AppUtil.getValues(objectNodeCategory.toString()));
			lcategory.add(category);
			Assert.assertNotNull(category);
		}

	}

	private void addTasks(int count) {

		for (int i = ltask.size(); i < ltask.size()+count; i++) {
			ObjectNode objectNodeTask = mapper.createObjectNode();
			Company com = lcompany.get(getRandomNumberInRange(0, lcompany.size()- 1));
			
			objectNodeTask.put("name", "task " + i);
			objectNodeTask.put("content", "some text : ))))))");
			objectNodeTask.put("owner_id", com.getId());

			ArrayNode arrayCategoriesID = objectNodeTask.putArray("category_id");

			for (int k = 0; k <= getRandomNumberInRange(1, lcategory.size()); k++) {
				Category c = lcategory.get(getRandomNumberInRange(0, lcategory.size()- 1));
				System.out.println("I " + i + " k " + k + " value " + c.getId());
				arrayCategoriesID.add(c.getId());
			}
			Assert.assertNotNull(taskService.create(AppUtil.getValues(objectNodeTask.toString())));
		}
	}
	
	private void deleteCompany() {
		if(!lcompany.isEmpty())
			Assert.assertTrue(companyService.delete(lcompany.get(getRandomNumberInRange(0, lcompany.size()-1))));
	}
	
	private void deleteCategory() {
		if(!lcategory.isEmpty())
			Assert.assertTrue(categoryService.delete(lcategory.get(getRandomNumberInRange(0, lcategory.size()-1))));
	}
	
	private void deleteTask() {
		if(!ltask.isEmpty())
			Assert.assertTrue(taskService.delete(ltask.get(getRandomNumberInRange(0, ltask.size()-1))));
	}
	
	private void updateCompany() {
		if(!lcompany.isEmpty()) {
			Company company = lcompany.get(getRandomNumberInRange(0, lcompany.size()-1));
			company.setAddress("TEST");
			companyService.update(company);
		}
	}
	

	@Test
	private void test() {
		start();
		addCompanies(1);
		//updateCompany();
	}

}

