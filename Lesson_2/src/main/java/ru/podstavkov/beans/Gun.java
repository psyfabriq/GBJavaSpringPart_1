package ru.podstavkov.beans;

public interface Gun {
	Bullet getBullet();
	void setBullet(Bullet bullet);
	void doShoot();
	void breaking();
	boolean isBroken();
	void ready();
}
