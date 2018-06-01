package ru.podstavkov.beans;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("saintlybullet")
@Scope("prototype")
public class SaintlyBullet implements Bullet {
	
	@Value("13")
	private int chargeCol;
	
	@Value("false")
	private boolean empty;
	
	@Value("Saintly")
	private  String name;
	
	public int getCharge() {
		return chargeCol;
	}
	public boolean isEpty() {
		return empty;
	}
	public void processing() {
		if(chargeCol <= 0) {
			empty = true;
			return;
		}
		--chargeCol;
		System.out.println("Осталось патрон "+chargeCol);		

	}
	public String getTypeBullet() {
		return name;
	}
	@PostConstruct
	public void ready() {
		System.out.println(name+" Обойма подготовлена!");		
	}
}
