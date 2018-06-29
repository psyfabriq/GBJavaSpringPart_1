package ru.podstavkov.service;

import org.springframework.beans.factory.annotation.Autowired;

import ru.podstavkov.dao.CategoryDAO;
import ru.podstavkov.dao.CompanyDAO;
import ru.podstavkov.dao.TaskDAO;
import ru.podstavkov.entity.Category;
import ru.podstavkov.entity.Company;
import ru.podstavkov.entity.Task;

public abstract class AbstractAppService<T> implements AppService<T> {
	
	protected TaskDAO taskDAO;
	
	protected CompanyDAO companyDAO;

	protected CategoryDAO categoryDAO;
	
	@Autowired
	public void setTaskDAO(TaskDAO daoToSet) {
		taskDAO = daoToSet;
		taskDAO.setClazz(Task.class);
	}

	@Autowired
	public void setCompanyDAO(CompanyDAO daoToSet) {
		companyDAO = daoToSet;
		companyDAO.setClazz(Company.class);
	}

	@Autowired
	public void setCategoryDAO(CategoryDAO daoToSet) {
		categoryDAO = daoToSet;
		categoryDAO.setClazz(Category.class);
	}
	
	@Override
	public T create(T entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
