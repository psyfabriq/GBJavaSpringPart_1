package ru.podstavkov.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.podstavkov.entity.Category;
import ru.podstavkov.entity.Task;

@Repository("adDAO")
@Transactional
public class TaskDAO extends AbstractDAO{
	
	public List<Task> getListTask() {

		return em.createQuery("SELECT e FROM Task e  WHERE e.active = true ", Task.class).getResultList();
	}

	public Task merge(Task task) {
		return em.merge(task);
	}
	
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
        update.where(cb.greaterThanOrEqualTo(e.get("id"), task.getId()));
        // perform update
        this.em.createQuery(update).executeUpdate();
	}

	public List<Task> getTaskById(String ...id) {
		if (id == null)
			return null;
		List<Task> task = em.createQuery("SELECT p FROM Task p WHERE p.active = true AND p.id IN :ids",Task.class).setParameter("ids", new ArrayList<>(Arrays.asList(id))).getResultList();
		return task;
	}

	public void removeTask(Task task) {
		if (task == null)
			return;
		removeTask(task.getId());
	}

	public void removeTask(String adid) {
		if (adid == null || adid.isEmpty())
			return;
		
		 CriteriaBuilder cb = this.em.getCriteriaBuilder();
		 CriteriaDelete<Task> delete = cb.createCriteriaDelete(Task.class);
		 Root e = delete.from(Task.class);
		 delete.where(cb.lessThanOrEqualTo(e.get("id"), adid));
	     this.em.createQuery(delete).executeUpdate();


	}
}
