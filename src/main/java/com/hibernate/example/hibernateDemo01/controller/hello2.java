package com.hibernate.example.hibernateDemo01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello2 {

	
	@GetMapping("/helloworld")
	public String hello() {
		return "Hello World";
	}
}
