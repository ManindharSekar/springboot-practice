package com.example.spring_security.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_security.entity.User;
import com.example.spring_security.repository.UserRepository;
import com.example.spring_security.service.UserService;

@RestController
public class UserController {
	
	
	private UserService userService;

	public UserController(UserService userService, UserRepository userRepository) {
		super();
		this.userService = userService;
		this.userRepository = userRepository;
	}


	@Autowired
	UserRepository userRepository;

	@PostMapping("/register")
	public User register(@RequestBody User user) {
		//return userRepository.save(user);
		return userService.register(user);
	}
	
	  
	  @PostMapping("/login") 
	  public String login(@RequestBody User user) {

   
		  return userService.verify(user);
	  }
}
