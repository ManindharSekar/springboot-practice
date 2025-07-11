package com.example.spring_security.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring_security.entity.User;
import com.example.spring_security.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	private BCryptPasswordEncoder bcryptPassswordEncoder;
	
	private AuthenticationManager authenticationManager;
	
	private JwtService jwtService;




	public UserService(UserRepository userRepository, BCryptPasswordEncoder bcryptPassswordEncoder,
			AuthenticationManager authenticationManager, JwtService jwtService) {
		super();
		this.userRepository = userRepository;
		this.bcryptPassswordEncoder = bcryptPassswordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}




	

	public User register(User user) {
		user.setPassword(bcryptPassswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public String verify(User user) {
		
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
		  //var u=userRepository.findByUserName(user.getUserName());
		  if(authenticate.isAuthenticated()) {
		  return jwtService.generateToken(user); 
		  }
		  return "failure";
		  
		  }
		 
}
