package ru.podstavkov.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.podstavkov.entity.Task;

@Repository("adDAO")
@Transactional
public class TaskDAO extends AbstractDAO{
	
	public List<Task> getListAd() {

		return em.createQuery("SELECT e FROM Task e", Task.class).getResultList();
	}

	public Task merge(Task task) {
		return em.merge(task);
	}
	
	public void persist(Task task) {
		if (task == null)
			return;
		em.persist(task);
	}

	public Task getAdById(String id) {
		if (id == null)
			return null;
		return em.find(Task.class, id);
	}

	public void removeAd(Task task) {
		if (task == null)
			return;
		removeAd(task.getId());
	}

	public void removeAd(String adid) {
		if (adid == null || adid.isEmpty())
			return;
		Task task = em.find(Task.class, adid);
		em.remove(task);

	}
}
