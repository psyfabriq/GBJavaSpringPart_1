package ru.podstavkov.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.podstavkov.entity.Company;
import ru.podstavkov.entity.Task;

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
		
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        // create update
        CriteriaUpdate<Company> update = cb.createCriteriaUpdate(Company.class);
        Root e = update.from(Company.class);
        // set update and where clause
        update.set("description", company.getDescription());
        update.set("address", company.getAddress());
        update.set("name", company.getName());
        //update.set("amount", company.getTasks());
        update.where(cb.greaterThanOrEqualTo(e.get("id"), company.getId()));
        // perform update
        this.em.createQuery(update).executeUpdate();
	
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
		
		 CriteriaBuilder cb = this.em.getCriteriaBuilder();
		 CriteriaDelete<Company> delete = cb.createCriteriaDelete(Company.class);
		 Root e = delete.from(Company.class);
		 delete.where(cb.lessThanOrEqualTo(e.get("id"), companyid));
	     this.em.createQuery(delete).executeUpdate();

	}
}
