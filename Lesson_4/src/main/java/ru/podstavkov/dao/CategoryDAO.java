package ru.podstavkov.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.podstavkov.entity.Category;

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
		em.persist(category);
	}

	public Category getCategoryById(String id) {
		if (id == null)
			return null;
		return em.find(Category.class, id);
	}

	public void removeCategory(Category category) {
		if (category == null)
			return;
		removeCategory(category.getId());
	}

	public void removeCategory(String categoryid) {
		if (categoryid == null || categoryid.isEmpty())
			return;
		Category category = em.find(Category.class, categoryid);
		em.remove(category);
	}

}
