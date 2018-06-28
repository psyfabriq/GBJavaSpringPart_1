package ru.podstavkov.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.podstavkov.entity.Company;

@Repository
public class CompanyDAO extends AbstractDAO {

	public List<Company> getListCompany() {
		Session session = em.unwrap(Session.class);		
		Criteria criteria = session.createCriteria(Company.class);	
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) ;	
		return criteria.list();
	}
	
	public List<Company> getListCompany(int start, int count) {
		Session session = em.unwrap(Session.class);		
		Criteria criteria = session.createCriteria(Company.class);
		criteria.setFirstResult(start);
		criteria.setMaxResults(count);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) ;	
		return criteria.list();
	}

	public Company merge(Company company) {
		return em.merge(company);
	}

	/*
	public void persist(Company company) {
		if (company == null)
			return;
		
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaUpdate<Company> update = cb.createCriteriaUpdate(Company.class);
        Root e = update.from(Company.class);
        update.set("description", company.getDescription());
        update.set("address", company.getAddress());
        update.set("name", company.getName());
        update.where(cb.greaterThanOrEqualTo(e.get("id"), company.getId()));
        this.em.createQuery(update).executeUpdate();
	
	}
	*/

	public List<Company> getCompanyById(String ...id) {
		if (id == null)
			return null;
		
		Session session = em.unwrap(Session.class);		
		Criteria c = session.createCriteria(Company.class);
		c.setMaxResults(1);
		c.add(Restrictions.in("id",Arrays.asList(id)));
		return c.list();
	}

}
