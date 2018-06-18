package ru.podstavkov.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;

import ru.podstavkov.dao.CategoryDAO;
import ru.podstavkov.dao.CompanyDAO;
import ru.podstavkov.dao.TaskDAO;
import ru.podstavkov.entity.Category;
import ru.podstavkov.entity.Company;
import ru.podstavkov.entity.Task;

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
	@Transactional(readOnly=false)
	public boolean createCategory(Map<String, Object> map) {
	
		Category category = new Category();
		category.setName((String) map.get("name"));
		 Assert.assertNotNull((categoryDAO.merge(category)));

		return true;
	}

	@Override
	@Transactional(readOnly=false)
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
	@Transactional(readOnly=false)
	public boolean createTask(Map<String, Object> map) {
		//if (AppUtil.checkHasAllVariables(map, "name", "content"))
		//	return false;
		
		List<String> tList = (List<String>)map.get("category_id");		
		List<Category> listCategory = getlistCategoryByIDs(tList.toArray(new String[0]));
		Task task = new Task();
		task.setName((String) map.get("name"));
	    task.setCategory(listCategory);
		task.setContent((String) map.get("content"));
		task.setOwner(getCompany((String) map.get("owner_id")));
		taskDAO.merge(task);
		return true;
	}

	@Override
	@Transactional(readOnly=false)
	public boolean deleteCategory(String id) {
		categoryDAO.removeCategory(id);
		return true;
	}

	@Override
	@Transactional(readOnly=false)
	public boolean deleteCompany(String id) {
		companyDAO.removeCompany(id);
		return true;
	}

	@Override
	@Transactional(readOnly=false)
	public boolean deleteTask(String id) {
		taskDAO.removeTask(id);
		return true;
		}

	@Override
	@Transactional(readOnly=false)
	public void updateCategory(Map<String, Object> map) {
		Category category = new Category();
		category.setId((String)map.get("id"));
		category.setName((String)map.get("name"));
		categoryDAO.persist(category);
	}

	@Override
	@Transactional(readOnly=false)
	public void updateCompany(Map<String, Object> map) {
		Company company = new Company();
		company.setId((String)map.get("id"));
		company.setName((String)map.get("name"));
		company.setAddress((String)map.get("address"));
		company.setDescription((String)map.get("description"));
		companyDAO.persist(company);	
		}

	@Override
	@Transactional(readOnly=false)
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
	@Transactional(readOnly=true)
	public Category getCategory(String id) {
		return categoryDAO.getCategoryById(id).get(1);
	}

	@Override
	@Transactional(readOnly=true)
	public Company getCompany(String id) {
		return companyDAO.getCompanyById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Task getTask(String id) {
		return taskDAO.getTaskById(id).get(1);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Collection<Task> getTasks(String ...ids) {
		return taskDAO.getTaskById(ids);
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Category> listCategory() {
		return categoryDAO.getListCategory();
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Company> listCompany() {
		return companyDAO.getListCompany();
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Task> listTask() {
		return taskDAO.getListTask();
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Task> getListTaskByCategory(String category_id) {
		return getListTaskByCategory(categoryDAO.getCategoryById(category_id).get(1));
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Task> getListTaskByCategory(Category category) {
		return category.getTasks();
	}

	@Override
	@Transactional(readOnly=true)
	public Company getCompanyByTask(String task_id) {
		return getCompanyByTask(taskDAO.getTaskById(task_id).get(1));
	}

	@Override
	@Transactional(readOnly=true)
	public Company getCompanyByTask(Task task) {
		return task.getOwner();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Category> getlistCategoryByIDs(String... ids) {
		return categoryDAO.getCategoryById(ids);
	}

}
