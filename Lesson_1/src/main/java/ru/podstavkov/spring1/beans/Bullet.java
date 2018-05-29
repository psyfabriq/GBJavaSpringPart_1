package ru.podstavkov.spring1.beans;

import org.springframework.stereotype.Component;

@Component("bullet")
public class Bullet {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
