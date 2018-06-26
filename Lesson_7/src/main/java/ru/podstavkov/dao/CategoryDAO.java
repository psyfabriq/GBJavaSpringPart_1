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
import org.springframework.transaction.annotation.Transactional;

import ru.podstavkov.entity.Category;

@Component
@Transactional
public class CategoryDAO extends AbstractDAO {

	public List<Category> getListCategory() {
		Session session = em.unwrap(Session.class);		
		Criteria criteria = session.createCriteria(Category.class);	
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) ;	
		return criteria.list();
	}
	
	public List<Category> getListCategory(int start, int count) {
		Session session = em.unwrap(Session.class);		
		Criteria criteria = session.createCriteria(Category.class);
		criteria.setFirstResult(start);
		criteria.setMaxResults(count);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) ;	
		return criteria.list();
	}
	public Category merge(Category category) {
		return em.merge(category);
	}

	/*
	public void persist(Category category) {
		if (category == null)
			return;
		    
		    CriteriaBuilder cb = this.em.getCriteriaBuilder();
	        CriteriaUpdate<Category> update = cb.createCriteriaUpdate(Category.class);
	        Root e = update.from(Category.class);
	        update.set("name", category.getName());
	        update.where(cb.greaterThanOrEqualTo(e.get("id"), category.getId()));
	        this.em.createQuery(update).executeUpdate();
	}
	*/

	public List<Category> getCategoryById(String ...id) {
		if (id == null)
			return null;
		Session session = em.unwrap(Session.class);		
		Criteria criteria = session.createCriteria(Category.class);
		criteria.setMaxResults(1);
		criteria.add(Restrictions.in("id",Arrays.asList(id)));
		return criteria.list();
	}



}
