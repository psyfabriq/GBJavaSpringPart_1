package ru.podstavkov.service;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.podstavkov.dao.CategoryDAO;
import ru.podstavkov.dao.CompanyDAO;
import ru.podstavkov.dao.TaskDAO;
import ru.podstavkov.entity.Category;
import ru.podstavkov.entity.Company;
import ru.podstavkov.entity.Task;
import ru.podstavkov.entity.exeption.BuilderExeption;

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
	@Transactional(readOnly = false)
	public Category createCategory(Map<String, Object> map) {

		Category category;
		try {
			category = Category.getBuilder().setName((String) map.get("name")).build();
			return categoryDAO.merge(category);
		} catch (BuilderExeption e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public Category createCategory(Category category) {
		return categoryDAO.merge(category);
	}

	@Override
	@Transactional(readOnly = false)
	public Company createCompany(Map<String, Object> map) {

		Company company;
		try {
			company = Company.getBuilder().setName((String) map.get("name")).setAddress((String) map.get("address"))
					.setDescription((String) map.get("description")).build();
			return companyDAO.merge(company);
		} catch (BuilderExeption e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public Company createCompany(Company company) {
		return companyDAO.merge(company);
	}

	@Override
	@Transactional(readOnly = false)
	public Task createTask(Map<String, Object> map) {

		List<String> tList = (List<String>) map.get("category_id");
		List<Category> listCategory = getlistCategoryByIDs(tList.toArray(new String[0]));
		Task task;
		try {
			task = Task.getBuilder().setName((String) map.get("name")).setCategory(listCategory)
					.setContent((String) map.get("content")).setOwner(getCompany((String) map.get("owner_id"))).build();
			return taskDAO.merge(task);
		} catch (BuilderExeption e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	@Transactional(readOnly = false)
	public Task createTask(Task task) {
		return taskDAO.merge(task);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteCategory(String id) {
		categoryDAO.removeCategory(id);
		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteCompany(String id) {
		companyDAO.removeCompany(id);
		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteTask(String id) {
		taskDAO.removeTask(id);
		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public void updateCategory(Map<String, Object> map) {
		Category category;
		try {
			category = Category.getBuilder().setId((String) map.get("id")).setName((String) map.get("name")).build();
			categoryDAO.persist(category);
		} catch (BuilderExeption e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void updateCompany(Map<String, Object> map) {
		Company company;
		try {
			company = Company.getBuilder().setId((String) map.get("id")).setName((String) map.get("name"))
					.setAddress((String) map.get("address")).setDescription((String) map.get("description")).build();
			companyDAO.persist(company);
		} catch (BuilderExeption e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void updateTask(Map<String, Object> map) {
		Task task;
		try {
			task = Task.getBuilder().setId((String) map.get("id")).setName((String) map.get("name"))
					.setContent((String) map.get("content")).setOwner(getCompany((String) map.get("owner_id"))).build();
			taskDAO.persist(task);
		} catch (BuilderExeption e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	@Override
	@Transactional(readOnly = false)
	public void updateCategory(Category category) {
			categoryDAO.persist(category);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateCompany(Company company) {
			companyDAO.persist(company);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateTask(Task task) {
			taskDAO.persist(task);
	}
	
	
	
	

	@Override
	@Transactional(readOnly = true)
	public Category getCategory(String id) {
		return categoryDAO.getCategoryById(id).get(0);
	}

	@Override
	@Transactional(readOnly = true)
	public Company getCompany(String id) {
		return companyDAO.getCompanyById(id).get(0);
	}

	@Override
	@Transactional(readOnly = true)
	public Task getTask(String id) {
		return taskDAO.getTaskById(id).get(0);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Task> getTasks(String... ids) {
		return taskDAO.getTaskById(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Category> listCategory() {
		return categoryDAO.getListCategory();
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Company> listCompany() {
		return companyDAO.getListCompany();
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Task> listTask() {
		return taskDAO.getListTask();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Task> listTask(Map<String, Object> map) {
		int start = (Integer) map.get("position");
		int count = (Integer) map.get("count");
		List<String> lCategory = (List<String>) map.get("selectedCategory");
		List<String> lCompany = (List<String>) map.get("selectedCompany");
		Optional<List<String>> idsCategory = Optional.ofNullable(!lCategory.isEmpty() ? lCategory : null);
		Optional<List<String>> idsCompany = Optional.ofNullable(!lCompany.isEmpty() ? lCompany : null);
		return taskDAO.selectListTask(start, count, idsCompany, idsCategory);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Task> getListTaskByCategory(String category_id) {
		return getListTaskByCategory(categoryDAO.getCategoryById(category_id).get(1));
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Task> getListTaskByCategory(Category category) {
		return category.getTasks();
	}

	@Override
	@Transactional(readOnly = true)
	public Company getCompanyByTask(String task_id) {
		return getCompanyByTask(taskDAO.getTaskById(task_id).get(1));
	}

	@Override
	@Transactional(readOnly = true)
	public Company getCompanyByTask(Task task) {
		return task.getOwner();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Category> getlistCategoryByIDs(String... ids) {
		return categoryDAO.getCategoryById(ids);
	}

	@Override
	public void cleanAll() {
		// TODO Auto-generated method stub

	}

}
