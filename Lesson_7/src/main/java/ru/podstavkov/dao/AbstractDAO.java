package ru.podstavkov.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;

public abstract class AbstractDAO {
    @PersistenceContext
    protected EntityManager em;
    
    protected void cleanAll() {
    	Session session = em.unwrap(Session.class);		
    	Query query = session.createQuery("delete ...");
    	query.executeUpdate();
	}
}
