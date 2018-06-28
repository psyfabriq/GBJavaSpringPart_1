package ru.podstavkov.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ru.podstavkov.dao.CategoryDAO;
import ru.podstavkov.entity.Category;
import ru.podstavkov.entity.exeption.BuilderExeption;

@Service
public class CategoryService implements AppService<Category> {

	@Autowired
	private CategoryDAO categoryDAO;

	@Override
	public Category create(Category entity) {
		return categoryDAO.merge(entity);
	}

	@Override
	public Category create(Map<String, Object> map) {
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
	public boolean delete(Category entity) {
		return categoryDAO.deleteById(Category.class,entity.getId());
	}

	@Override
	public boolean delete(Map<String, Object> map) {
		if(map.isEmpty() && map.containsKey("id"))
			return false;
		return categoryDAO.deleteById(Category.class,(String)map.get("id"));
	}

	@Override
	public boolean update(Category entity) {
		return categoryDAO.persist(Category.class,entity);
	}

	@Override
	public boolean update(Map<String, Object> map) {
		boolean result = false;
		Category category;
		try {
			category = Category.getBuilder().setId((String) map.get("id")).setName((String) map.get("name")).build();
			result = categoryDAO.persist(Category.class,category);
		} catch (BuilderExeption e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Category get(String id) {
		return categoryDAO.getCategoryById(id).get(0);
	}

	@Override
	public List<Category> getList() {
		return categoryDAO.getListCategory();
	}
	
	@Override
	public List<Category> getList(String ...ids) {
		return categoryDAO.getCategoryById(ids);
	}

	@Override
	public List<Category> getList(Map<String, Object> map) {
		int start = (Integer) map.get("position");
		int count = (Integer) map.get("count");
		return categoryDAO.getListCategory(start,count);
	}

}
