package ru.podstavkov.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task extends AbstractEntity {

	
	@Column(name = "content")
	private String content;
	
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinTable(
    		name = "tasks_categories",
            joinColumns = { @JoinColumn(name = "task_id") }, 
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
        )
    private  List<Category> category = new ArrayList();
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="owner_id")
    private Company owner;


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public Company getOwner() {
		return owner;
	}

	public void setOwner(Company owner) {
		this.owner = owner;
	}

	

	
	

}
