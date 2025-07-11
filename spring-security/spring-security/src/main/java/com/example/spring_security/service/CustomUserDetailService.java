package com.example.spring_security.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.spring_security.entity.User;
import com.example.spring_security.repository.UserRepository;

@Component
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private  UserRepository userRepository;

	public CustomUserDetailService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if(Objects.isNull(user)) {
			System.out.println("User Not Available");
			throw new UsernameNotFoundException("user not found");
		}
		return new CustomerUserDetails(user);
	}

}
