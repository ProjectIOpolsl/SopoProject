package com.grupa1.SopoProject.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = "com.grupa1.SopoProject")
@EntityScan(basePackages = "com.grupa1.SopoProject")
public class SopoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SopoProjectApplication.class, args);
	}
}
