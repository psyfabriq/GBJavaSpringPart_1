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
}
