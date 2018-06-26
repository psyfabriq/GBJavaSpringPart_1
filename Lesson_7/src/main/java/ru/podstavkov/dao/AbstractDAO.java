package ru.podstavkov.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.podstavkov.entity.EntityInterface;

public abstract class AbstractDAO {

	@PersistenceContext
	protected EntityManager em;



	public boolean deleteById(Class<?> type, Serializable id) {
		Session session = em.unwrap(Session.class);
		Object persistentInstance = session.load(type, id);
		if (persistentInstance != null) {
			session.delete(persistentInstance);
			return true;
		}
		return false;
	}

	public boolean persist(Class<?> type, EntityInterface entity) {
		boolean result = false;
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(type);
		criteria.add(Restrictions.eq("id", entity.getId()));
		Transaction tx = session.beginTransaction();
		try {
			tx.begin();
			session.update(entity);
			tx.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		session.close();
		return result;
	}
}
