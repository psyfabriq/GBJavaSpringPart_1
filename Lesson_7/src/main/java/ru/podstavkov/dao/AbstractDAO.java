package ru.podstavkov.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.podstavkov.entity.AbstractEntity;
import ru.podstavkov.entity.Category;
import ru.podstavkov.entity.EntityInterface;

@Transactional
public abstract class AbstractDAO<T extends Serializable> {

	@PersistenceContext
	protected EntityManager em;

	private Class<T> clazz;

	public void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public  boolean delete( Serializable id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaDelete<T> delete = cb.createCriteriaDelete(clazz);
	    Root<T> e = delete.from(clazz);
	    delete.where(cb.equal(e.get("id"), id));
	    return em.createQuery(delete).executeUpdate()==0?false:true;
	}

	public boolean persist( EntityInterface entity) {
		boolean result = false;
		//if (entity == null) {
		    CriteriaBuilder cb = this.em.getCriteriaBuilder();
	        CriteriaUpdate<T> update = cb.createCriteriaUpdate(clazz);
	        Root e = update.from(clazz);
	         update.set("address", "TESTTTTT");
	        update.where(cb.greaterThanOrEqualTo(e.get("id"), entity.getId()));
	        result = this.em.createQuery(update).executeUpdate() > 0?true:false;
	        System.out.println(entity.getId());
	//	}
		
		/*
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(type);
		criteria.add(Restrictions.eq("id", entity.getId()));
		// Transaction tx = session.beginTransaction();
		try {
			// tx.begin();
			session.update(entity);
			// tx.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			// tx.rollback();
		}

		session.close();
		
		*/
		return result;
	}
	
	public T merge(T entity) {
		System.out.println("asdasdasdasdasdddddddddddDDDDDDDDDDDDDDDDDDDDDSAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		return em.merge(entity);
	}
	

	public void clearAll() {
		em.clear();
	}
}
