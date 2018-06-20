package ru.podstavkov.entity;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import ru.podstavkov.entity.exeption.BuilderExeption;
import ru.podstavkov.utils.AppUtil;

@Entity
@Table(name = "task")
public class Task extends AbstractEntity {

	
	@Column(name = "content")
	private String content;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name="published_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date publishedDate;
	
	@Column(name="published_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date endDate;
	
	
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinTable(
    		name = "tasks_categories",
            joinColumns = { @JoinColumn(name = "task_id") }, 
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
        )
    @JsonBackReference
    private  List<Category> category = new ArrayList();
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="owner_id")
    @JsonBackReference
    private Company owner;
    
 
	private Task() {
		super();
		this.active = true;
	}
	
    public static Builder getBuilder() {
        return new Task().new Builder();
    }

	public String getContent() {
		return content;
	}

	public List<Category> getCategory() {
		return category;
	}

	public Company getOwner() {
		return owner;
	}
	


	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Date getPublishedDate() {
		return publishedDate;
	}


	public Date getEndDate() {
		return endDate;
	}



	public class Builder {
		
		

		public Builder() {
			super();
			Task.this.publishedDate = new Date();
		}

		public Builder setId(String id) {
			Task.this.setId(id);
			return this;
		}
		
		public Builder setName(String name) {
			Task.this.setName(name);
			return this;
		}
		
		public Builder setOwner(Company owner) {
			Task.this.owner = owner;
			return this;
		}
		
		public Builder setCategory(List<Category> category) {
			Task.this.category = category;
			return this;
		}
		
		public Builder setContent(String content) {
			Task.this.content = content;
			return this;
		}
		
		public Task build() throws BuilderExeption, NoSuchAlgorithmException {
			
			if ("".equals(Task.this.getName())||Task.this.getName() == null) {
				throw new BuilderExeption("Name Task coud`t  be empty!");
			}
			
			if (Task.this.owner == null) {
				throw new BuilderExeption("Not set Owner!");
			}
			
			if (Task.this.category == null || Task.this.category.size() == 0 ) {
				throw new BuilderExeption("Not set Category!");
			}
			
			Task.this.setHash(AppUtil.hashString(Task.this.getName()+Task.this.getOwner().getId()));
			
			
			 return Task.this;
		}
	}
	
	

	
	

}
