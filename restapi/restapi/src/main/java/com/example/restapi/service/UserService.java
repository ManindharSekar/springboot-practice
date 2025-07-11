package com.example.restapi.service;

import java.util.List;

import com.example.restapi.entity.User;

public interface UserService {
	
	User createUser(User user);
	
	User getUserById(Long userId);
	
	List<User> getAllUser();
	
	User updateUser(User user);
	
	void deleteUser(Long userId);

}
