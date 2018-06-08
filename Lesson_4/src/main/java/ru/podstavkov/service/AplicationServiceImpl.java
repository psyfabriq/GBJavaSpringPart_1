package ru.podstavkov.service;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.testng.Assert;

import ru.podstavkov.dao.TaskDAO;
import ru.podstavkov.entity.Category;
import ru.podstavkov.entity.Company;
import ru.podstavkov.entity.Task;
import ru.podstavkov.utils.AppUtil;
import ru.podstavkov.dao.CategoryDAO;
import ru.podstavkov.dao.CompanyDAO;

@Service
@Component(value = "appservice")
public class AplicationServiceImpl implements AplicationService {

	@Autowired
	private TaskDAO taskDAO;

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private CompanyDAO companyDAO;

	@Override
	public boolean createCategory(Map<String, Object> map) {
	
		Category category = new Category();
		category.setName((String) map.get("name"));
		 Assert.assertNotNull((categoryDAO.merge(category)));

		return true;
	}

	@Override
	public boolean createCompany(Map<String, Object> map) {
	//	if (AppUtil.checkHasAllVariables(map, "name", "address"))
	//		return false;

		Company company = new Company();
		company.setName((String) map.get("name"));
		company.setAddress((String) map.get("address"));
		company.setDescription((String) map.get("description"));
		companyDAO.merge(company);
		return true;
	}

	@Override
	public boolean createTask(Map<String, Object> map) {
		//if (AppUtil.checkHasAllVariables(map, "name", "content"))
		//	return false;

		Task task = new Task();
		task.setName((String) map.get("name"));
		// task.setCategory(getCategory((String) map.get("category_id")));
		task.setContent((String) map.get("content"));
		taskDAO.merge(task);
		return true;
	}

	@Override
	public boolean deleteCategory(String id) {
		return false;
	}

	@Override
	public boolean deleteCompany(String id) {
		return false;
	}

	@Override
	public boolean deleteTask(String id) {
		return false;
		}

	@Override
	public void updateCategory(Map<String, Object> map) {
		Category category = new Category();
		category.setId((String)map.get("id"));
		category.setName((String)map.get("name"));
		categoryDAO.persist(category);
	}

	@Override
	public void updateCompany(Map<String, Object> map) {
		Company company = new Company();
		company.setId((String)map.get("id"));
		company.setName((String)map.get("name"));
		company.setAddress((String)map.get("address"));
		company.setDescription((String)map.get("description"));
		companyDAO.persist(company);	
		}

	@Override
	public void updateTask(Map<String, Object> map) {
		Task task = new Task();
		task.setId((String)map.get("id"));
		task.setName((String)map.get("name"));
		task.setContent((String)map.get("content"));
		task.setOwner(getCompany((String)map.get("owner_id")));
		//task.setCategory(get);
		taskDAO.persist(task);
		}

	@Override
	public Category getCategory(String id) {
		return categoryDAO.getCategoryById(id);
	}

	@Override
	public Company getCompany(String id) {
		return companyDAO.getCompanyById(id);
	}

	@Override
	public Task getTask(String id) {
		return taskDAO.getAdById(id);
	}

	@Override
	public Collection<Category> listCategory() {
		return categoryDAO.getListCategory();
	}

	@Override
	public Collection<Company> listCompany() {
		return companyDAO.getListCompany();
	}

	@Override
	public Collection<Task> listTask() {
		return taskDAO.getListAd();
	}

	@Override
	public Collection<Task> getListTaskByCategory(String category_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Task> getListTaskByCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company getCompanyByTask(String ad_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company getCompanyByTask(Task task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Category> getlistCategoryByIDs(String... id) {
		return null;
	}

}
