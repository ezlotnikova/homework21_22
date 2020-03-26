package com.gmail.ezlotnikova.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gmail.ezlotnikova.springboot",
		"com.gmail.ezlotnikova.service",
		"com.gmail.ezlotnikova.repository"})
public class SpringbootModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootModuleApplication.class, args);
	}

}
