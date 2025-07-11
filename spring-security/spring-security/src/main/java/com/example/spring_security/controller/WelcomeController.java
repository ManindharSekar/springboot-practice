package com.example.spring_security.controller;


import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class WelcomeController {
	
	@GetMapping("/")
	public String welcome() {
		return "welcome to spring security";
	}
	
	@GetMapping("/csrf")
	public CsrfToken getCsrf(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}

}
