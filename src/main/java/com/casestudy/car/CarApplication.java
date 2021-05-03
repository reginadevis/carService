package com.casestudy.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = { "com.casestudy.controller", "com.casestudy.service", "com.casestudy.dto",
		"com.casestudy.mapper", "com.casestudy.validator", "com.casestudy.exception" })
@EnableJpaRepositories("com.casestudy.repository")
@EntityScan("com.casestudy.entity")
public class CarApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarApplication.class, args);
	}

}
