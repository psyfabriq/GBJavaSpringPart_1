package ru.podstavkov.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category extends AbstractEntity {
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH })
	@JoinTable(name = "tasks_categories", joinColumns = {
			@JoinColumn(name = "category_id") }, inverseJoinColumns = { @JoinColumn(name = "task_id") })
	private List<Task> Tasks = new ArrayList();

	public List<Task> getTasks() {
		return Tasks;
	}

	public void setTasks(List<Task> tasks) {
		Tasks = tasks;
	}


	
}
