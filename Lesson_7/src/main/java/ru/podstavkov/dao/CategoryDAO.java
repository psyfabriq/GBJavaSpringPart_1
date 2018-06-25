package ru.podstavkov.dao;

import java.util.ArrayList;
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
import ru.podstavkov.entity.Company;
import ru.podstavkov.entity.Task;

@Component
@Transactional
public class CategoryDAO extends AbstractDAO {

	public List<Category> getListCategory() {

		return em.createQuery("SELECT e FROM Category e", Category.class).getResultList();
	}

	public Category merge(Category category) {
		return em.merge(category);
	}

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

	public List<Category> getCategoryById(String ...id) {
		if (id == null)
			return null;
		Session session = em.unwrap(Session.class);		
		Criteria c = session.createCriteria(Category.class);
		c.setMaxResults(1);
		c.add(Restrictions.in("id",Arrays.asList(id)));
		return c.list();
	}

	public void removeCategory(Category category) {
		if (category == null)
			return;
		removeCategory(category.getId());
	}

	public void removeCategory(String categoryid) {
		if (categoryid == null || categoryid.isEmpty())
			return;
		
		 CriteriaBuilder cb = this.em.getCriteriaBuilder();
		 CriteriaDelete<Category> delete = cb.createCriteriaDelete(Category.class);
		 Root e = delete.from(Category.class);
		 delete.where(cb.lessThanOrEqualTo(e.get("id"), categoryid));
	     this.em.createQuery(delete).executeUpdate();
	     
	}

}
