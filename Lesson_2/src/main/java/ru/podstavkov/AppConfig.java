package ru.podstavkov;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ru.podstavkov.beans")
public class AppConfig {
	/*
	 * @Bean(name = "Gun2") public GunImpl getHouse() { final GunImpl gun = new
	 * GunImpl(); gun.setBullet(new SimpleBullet());; return gun; }
	 */
}
