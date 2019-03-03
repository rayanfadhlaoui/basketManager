package com.basket.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.basket.manager.entities")
@ComponentScan(basePackages = {
		"com.basket.manager.controllers",
		"com.basket.manager.services",
		"com.basket.manager.repositories",
})
public class BasketManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasketManagerApplication.class, args);
	}

}

