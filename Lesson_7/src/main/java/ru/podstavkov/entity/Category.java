package ru.podstavkov.entity;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import ru.podstavkov.entity.exeption.BuilderExeption;
import ru.podstavkov.utils.AppUtil;

@Entity
@Table(name = "category")
public class Category  extends AbstractEntity implements EntityInterface {

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "tasks_categories", joinColumns = { @JoinColumn(name = "category_id") }, inverseJoinColumns = {
			@JoinColumn(name = "task_id") })
	private List<Task> tasks = new ArrayList();
	
	
	public static Builder getBuilder() {
		return new Category().new Builder();
	}

	public List<Task> getTasks() {
		return tasks;
	}
	
	public Category rebuild() throws BuilderExeption, NoSuchAlgorithmException {
		
		if ("".equals(this.getName())||this.getName() == null) {
			throw new BuilderExeption("Name Category coud`t  be empty!");
		}
		
		this.setHash(AppUtil.hashString(this.getName()));
		
		return Category.this;
	}

	public class Builder {
		
		public Builder setId(String id) {
			Category.this.setId(id);
			return this;
		}
		
		public Builder setName(String name) {
			Category.this.setName(name);
			return this;
		}

		public Builder setTasks(List<Task> tasks) {
			Category.this.tasks = tasks;
			return this;
		}

		public Category build() throws BuilderExeption, NoSuchAlgorithmException {
			return Category.this.rebuild();
		}
	}

}
