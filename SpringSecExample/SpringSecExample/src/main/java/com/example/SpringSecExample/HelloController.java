package com.example.SpringSecExample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HelloController {
	
	
	@GetMapping("/")
	public String greet() {
		
		return "Wecome Manindhar  ";
	}

}
