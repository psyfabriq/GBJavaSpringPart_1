package ru.podstavkov.spring1.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value = "Gun")
public class Gun {

	@Autowired(required = false)
	@Qualifier(value = "bulletnull")
	private Bullet bullet;

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

}
