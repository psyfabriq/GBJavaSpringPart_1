package ru.podstavkov.beans;

public interface Bullet {
	int getCharge();
	void processing();
	boolean isEpty();
	void ready();
	String getTypeBullet();
}
