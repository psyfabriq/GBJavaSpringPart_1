package ru.podstavkov.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.podstavkov.entity.Category;
import ru.podstavkov.entity.Task;

@Repository
public class TaskDAO extends AbstractDAO{
	
	public List<Task> getListTask() {

		return em.createQuery("SELECT e FROM Task e  WHERE e.active = true ", Task.class).getResultList();
	}
	
	public List<Task> selectListTask(int start, int count, Optional<List<String>>  idsCompany, Optional<List<String>> idsCategory) {
		
		Session session = em.unwrap(Session.class);		
		Criteria c = session.createCriteria(Task.class);
		c.setFirstResult(start);
		c.setMaxResults(count);
		c.addOrder(Order.desc("publishedDate"));
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) ;	
		
		
		if(idsCategory.isPresent())
			c.createAlias("category", "categoryAlias");
		
		if(idsCompany.isPresent())
			c.createAlias("owner", "companyAlias");
		
		
		idsCategory.ifPresent(v -> {c.add(Restrictions.in("categoryAlias.id", v));});
		idsCompany.ifPresent(v -> {c.add(Restrictions.in("companyAlias.id", v));});
		
		//idsCategory.ifPresent(v -> v.forEach(e -> { c.add(Restrictions.eq("categoryAlias.id", e)); }));
		//idsCompany.ifPresent(v -> v.forEach(e -> {c.add(Restrictions.in("companyAlias.id", e));}));
		
		
		return c.list();
		//return em.createQuery("SELECT e FROM Task e  WHERE e.active = true ", Task.class).getResultList();
	}

	public Task merge(Task task) {
		return em.merge(task);
	}
	/*
	public void persist(Task task) {
		if (task == null)
			return;
		
	    CriteriaBuilder cb = this.em.getCriteriaBuilder();
        // create update
        CriteriaUpdate<Task> update = cb.createCriteriaUpdate(Task.class);
        Root e = update.from(Task.class);
        // set update and where clause
        update.set("name", task.getName());
        update.set("content", task.getContent());
        update.set("owner_id", task.getOwner());
        update.set("active", task.isActive());
        update.where(cb.greaterThanOrEqualTo(e.get("id"), task.getId()));
        // perform update
        this.em.createQuery(update).executeUpdate();
	}
	*/

	public List<Task> getTaskById(String ...id) {
		if (id == null)
			return null;
		List<Task> task = em.createQuery("SELECT p FROM Task p WHERE p.active = true AND p.id IN :ids",Task.class).setParameter("ids", new ArrayList<>(Arrays.asList(id))).getResultList();
		return task;
	}


}
