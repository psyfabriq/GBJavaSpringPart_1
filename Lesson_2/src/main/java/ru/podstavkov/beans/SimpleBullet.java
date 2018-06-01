package ru.podstavkov.beans;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.podstavkov.annotation.UnproducableBullet;

@Component("simplebullet")
@Scope("prototype")
@UnproducableBullet(usingBulletClass=SaintlyBullet.class)
public class SimpleBullet implements Bullet  {
	@Value("12")
	private int chargeCol;
	@Value("false")
	private boolean empty;
	@Value("Simple")
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
