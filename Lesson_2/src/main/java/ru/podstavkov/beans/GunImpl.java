package ru.podstavkov.beans;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "my_gun")
public class GunImpl implements Gun {

	@Autowired(required = false)
	@Qualifier(value = "simplebullet") // Simple Buulet будет подменен Saintly Bullet
	private Bullet bullet;

	@Value("false")
	private boolean broken;

	public Bullet getBullet() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setBullet(Bullet bullet) {
		// TODO Auto-generated method stub

	}

	public void doShoot() {
		if (isBroken()) {
			System.out.println("Пистолет сломан!");
			return;
		}
		System.out.println("Сделан выстрил!");
		bullet.processing();
	}

	public void breaking() {
		this.broken = true;
	}

	public boolean isBroken() {
		return broken;
	}
	@PostConstruct
	public void ready() {
		System.out.println("Пистолет готов к использованию!");
		
	}

}
