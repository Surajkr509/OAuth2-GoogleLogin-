package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
	
	@GetMapping("/")
	public String hello() {
		return "Hello";
	}
	
	@GetMapping("/googleLogin")
	public String googleLogin() {
		return "Hello Sj after login Success";
	}

}
