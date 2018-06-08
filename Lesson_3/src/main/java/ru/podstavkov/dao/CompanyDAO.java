package ru.podstavkov.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.podstavkov.entity.Company;

@Component
@Transactional
public class CompanyDAO extends AbstractDAO {

	public List<Company> getListCompany() {
		return em.createQuery("SELECT e FROM Company e", Company.class).getResultList();
	}

	public Company merge(Company company) {
		return em.merge(company);
	}

	public void persist(Company company) {
		if (company == null)
			return;
		em.persist(company);
	}

	public Company getCompanyById(String id) {
		if (id == null)
			return null;
		return em.find(Company.class, id);
	}

	public void removeCompany(Company company) {
		if (company == null)
			return;
		removeCompany(company.getId());
	}

	public void removeCompany(String companyid) {
		if (companyid == null || companyid.isEmpty())
			return;
		Company company = em.find(Company.class, companyid);
		em.remove(company);

	}
}
