package ru.podstavkov.entity;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import ru.podstavkov.entity.exeption.BuilderExeption;
import ru.podstavkov.utils.AppUtil;

@Entity
@Table(name = "company")
public class Company extends AbstractEntity implements EntityInterface {
	
	

	@Column(name = "description")
	private String description;

	@Column(name = "address")
	private String address;

	@OneToMany(mappedBy = "owner",fetch=FetchType.EAGER , cascade=CascadeType.PERSIST)
	@JsonManagedReference
	private List<Task> tasks = new ArrayList();
	

    public static Builder getBuilder() {
        return new Company().new Builder();
    }


	public String getDescription() {
		return description;
	}

	public String getAddress() {
		return address;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Company rebuild() throws BuilderExeption, NoSuchAlgorithmException {
		
		if ("".equals(this.getName())||this.getName() == null) {
			throw new BuilderExeption("Name Company coud`t  be empty!");
		}
		
		this.setHash(AppUtil.hashString(this.getName()));

		return this;
	}


	public class Builder {
		
		public Builder setId(String id) {
			Company.this.setId(id);
			return this;
		}
		
		public Builder setName(String name)  {
			Company.this.setName(name);
			return this;
		}
		
		public Builder setTasks(List<Task> tasks) {
			Company.this.tasks = tasks;
			return this;
		}
		
		public Builder setAddress(String address) {
			Company.this.address = address;
			return this;
		}
		
		
		public Builder setDescription(String description) {
			Company.this.description = description;
			return this;
		}
		
		public Company build() throws BuilderExeption, NoSuchAlgorithmException {	
			return Company.this.rebuild();
		}
	}


	@Override
	public String toString() {
		return "Name: "+ getName()+" Address: "+getAddress()+" Description: "+getDescription();
	}

	
	
}
