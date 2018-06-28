package ru.podstavkov.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.podstavkov.dao.CompanyDAO;
import ru.podstavkov.entity.Company;
import ru.podstavkov.entity.exeption.BuilderExeption;

@Service
public class CompanyService implements AppService<Company> {

	@Autowired
	private CompanyDAO companyDAO;

	@Override
	public Company create(Company entity) {
		return companyDAO.merge(entity);
	}

	@Override
	public Company create(Map<String, Object> map) {
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
	public boolean delete(Company entity) {
		return companyDAO.deleteById(Company.class,entity.getId());

	}

	@Override
	public boolean delete(Map<String, Object> map) {
		if(map.isEmpty() && map.containsKey("id"))
			return false;
		return companyDAO.deleteById(Company.class,(String)map.get("id"));
	}

	@Override
	public boolean update(Company entity) {
		return companyDAO.persist(Company.class,entity);
	}

	@Override
	public boolean update(Map<String, Object> map) {
		boolean result = false;
		Company company;
		try {
			company = Company.getBuilder().setId((String) map.get("id")).setName((String) map.get("name"))
					.setAddress((String) map.get("address")).setDescription((String) map.get("description")).build();
			result = companyDAO.persist(Company.class,company);
		} catch (BuilderExeption e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Company get(String id) {
		return companyDAO.getCompanyById(id).get(0);
	}

	@Override
	public List<Company> getList() {
		return companyDAO.getListCompany();
	}
	
	@Override
	public List<Company> getList(String  ...ids) {
		return companyDAO.getCompanyById(ids);
	}

	@Override
	public List<Company> getList(Map<String, Object> map) {
		int start = (Integer) map.get("position");
		int count = (Integer) map.get("count");
		return companyDAO.getListCompany(start,count);
	}

}
