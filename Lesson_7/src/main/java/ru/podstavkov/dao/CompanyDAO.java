package ru.podstavkov.dao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.podstavkov.entity.Company;

@Repository
public class CompanyDAO extends AbstractDAO<Company> {

	public CompanyDAO() {
		super();
		setClazz(Company.class);
	}

	public List<Company> getListCompany() {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Company.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	public List<Company> getListCompany(int start, int count) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Company.class);
		criteria.setFirstResult(start);
		criteria.setMaxResults(count);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}




	public List<Company> getCompanyById(String... id) {
		if (id == null)
			return null;

		Session session = em.unwrap(Session.class);
		Criteria c = session.createCriteria(Company.class);
		c.setMaxResults(1);
		c.add(Restrictions.in("id", Arrays.asList(id)));
		return c.list();
	}

}
