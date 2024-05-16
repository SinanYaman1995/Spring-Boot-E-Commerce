package com.project.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan({"com.project.ecommerce.dao.UserRepository"})
@ComponentScan({"com.project.ecommerce.controllers"})
@SpringBootApplication
public class E_Commerce_Project_Application {

	public static void main(String[] args) {
		SpringApplication.run(E_Commerce_Project_Application.class, args);
	}

}
