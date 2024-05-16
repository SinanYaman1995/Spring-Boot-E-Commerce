package com.project.ecommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main_Page_Without_Authentication {

	@GetMapping(value = "/")
	 public String main_Page() {
		return "You are not identified, please send a post request to /auth/login with your login details";
	}
}