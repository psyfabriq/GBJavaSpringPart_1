package ru.podstavkov.spring1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ru.podstavkov.spring1.beans.Bullet;
import ru.podstavkov.spring1.beans.Gun;

@Configuration
@ComponentScan("ru.podstavkov.spring1.beans")
public class AppConfig {

    @Bean(name = "Gun2")
    public Gun getHouse() {
        final Gun gun = new Gun();
        gun.setBullet(new Bullet());;
        return gun;
    }
}
