package ru.podstavkov.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ru.podstavkov.config.AppConfig;

@Configuration
@EnableTransactionManagement
@ComponentScan("ru.podstavkov.test.dao,ru.podstavkov.test.service")
@PropertySource("classpath:db-conf.properties")
public class AppTestConfig extends AppConfig {

}
