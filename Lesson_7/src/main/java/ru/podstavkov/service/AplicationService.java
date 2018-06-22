package ru.podstavkov.service;

import java.util.Collection;
import java.util.Map;

import ru.podstavkov.entity.Task;
import ru.podstavkov.entity.Category;
import ru.podstavkov.entity.Company;

public interface AplicationService {
	
	Category createCategory(Map<String, Object> map);
	Company createCompany(Map<String, Object> map);
	Task createTask(Map<String, Object> map);
	
	boolean deleteCategory(String id);
	boolean deleteCompany(String id);
	boolean deleteTask(String id);
	
	void updateCategory(Map<String, Object> map);
	void updateCompany(Map<String, Object> map);
	void updateTask(Map<String, Object> map);
	
	Category getCategory(String id);
	Company getCompany (String id);
	Task getTask(String id);
	Collection<Task>  getTasks(String ...ids);
	
	Collection<Category> listCategory();
	Collection<Company>  listCompany();
	Collection<Task>     listTask();
	Collection<Task>     listTask(Map<String, Object> map);	
	
	Collection<Task>       getListTaskByCategory(String category_id);
	Collection<Task>       getListTaskByCategory(Category category);
	Collection<Category>   getlistCategoryByIDs(String ...id);
	
	Company getCompanyByTask(String ad_id);
	Company getCompanyByTask(Task task);
	
}
