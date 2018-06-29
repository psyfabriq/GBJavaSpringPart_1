package ru.podstavkov.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
@Component("taskservice")
public class TaskService extends AbstractAppService<Task> {
	
	@Override
	public Task create(Task entity) {
		return taskDAO.merge(entity);
	}

	@Override
	public Task create(Map<String, Object> map) {
		List<String> tList = (List<String>) map.get("category_id");
		List<Category> listCategory = getCategories(tList.toArray(new String[0]));
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
	public boolean delete(Task entity) {
		return taskDAO.delete(entity.getId());
	}

	@Override
	public boolean delete(Map<String, Object> map) {
		if (map.isEmpty() && map.containsKey("id"))
			return false;
		return taskDAO.delete( (String) map.get("id"));
	}

	@Override
	public boolean update(Task entity) {
		return taskDAO.persist( entity);
	}

	@Override
	public boolean update(Map<String, Object> map) {
		boolean result = false;
		Task task;
		try {
			task = Task.getBuilder().setId((String) map.get("id")).setName((String) map.get("name"))
					.setContent((String) map.get("content")).setOwner(getCompany((String) map.get("owner_id"))).build();
			result = taskDAO.persist(task);
		} catch (BuilderExeption e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Task get(String id) {
		return taskDAO.getTaskById(id).get(0);
	}

	@Override
	public List<Task> getList() {
		return taskDAO.getListTask();
	}
	
	@Override
	public List<Task> getList(String ...ids) {
		return taskDAO.getTaskById(ids);
	}

	@Override
	public List<Task> getList(Map<String, Object> map) {
		int start = (Integer) map.get("position");
		int count = (Integer) map.get("count");
		List<String> lCategory = (List<String>) map.get("selectedCategory");
		List<String> lCompany = (List<String>) map.get("selectedCompany");
		Optional<List<String>> idsCategory = Optional.ofNullable(!lCategory.isEmpty() ? lCategory : null);
		Optional<List<String>> idsCompany = Optional.ofNullable(!lCompany.isEmpty() ? lCompany : null);
		return taskDAO.selectListTask(start, count, idsCompany, idsCategory);

	}
	
	private Company getCompany(String id) {
		return companyDAO.getCompanyById(id).get(0);
	}
	
	private List<Category> getCategories(String ...ids) {
		return categoryDAO.getCategoryById(ids);
	}



}
