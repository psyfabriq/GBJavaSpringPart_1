package ru.podstavkov.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.podstavkov.entity.Task;
import ru.podstavkov.entity.Category;
import ru.podstavkov.entity.Company;

public interface AplicationService {
	
	Category createCategory(Map<String, Object> map);
	Category createCategory(Category category);
	Company createCompany(Map<String, Object> map);
	Company createCompany(Company company);
	Task createTask(Map<String, Object> map);
	Task createTask(Task task);
	
	boolean deleteCategory(String id);
	boolean deleteCompany(String id);
	boolean deleteTask(String id);
	
	void updateCategory(Map<String, Object> map);
	void updateCompany(Map<String, Object> map);
	void updateTask(Map<String, Object> map);
	
	void updateCategory(Category category);
	void updateCompany(Company company);
	void updateTask(Task task);
	
	Category getCategory(String id);
	Company getCompany (String id);
	Task getTask(String id);
	Collection<Task>  getTasks(String ...ids);
	
	Collection<Category> listCategory();
	Collection<Company>  listCompany();
	Collection<Task>     listTask();
	List<Task>     listTask(Map<String, Object> map);	
	
	Collection<Task>       getListTaskByCategory(String category_id);
	Collection<Task>       getListTaskByCategory(Category category);
	Collection<Category>   getlistCategoryByIDs(String ...id);
	
	Company getCompanyByTask(String ad_id);
	Company getCompanyByTask(Task task);
	
	void cleanAll();
	
}
