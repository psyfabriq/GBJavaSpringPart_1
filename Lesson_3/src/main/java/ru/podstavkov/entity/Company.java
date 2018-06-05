package ru.podstavkov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Company extends AbstractEntity {

	@Column
	private String description;
	@Column
	private String address;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	
}
