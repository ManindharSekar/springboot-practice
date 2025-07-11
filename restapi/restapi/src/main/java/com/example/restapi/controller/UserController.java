package com.example.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.entity.User;
import com.example.restapi.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
	 public UserService userService;
	
	 @PostMapping
	 public ResponseEntity<User> createUsers(@RequestBody User user){ 
		 User saveUser = userService.createUser(user);
		return new ResponseEntity<User>(saveUser,HttpStatus.CREATED);
	 }
	 
	 @GetMapping("{id}")
	 public ResponseEntity<User> getUserById(@PathVariable("id") Long userId){
		 User user = userService.getUserById(userId);
		return new ResponseEntity<User>(user,HttpStatus.OK);
		 
	 }
	 
	 @GetMapping
	 public ResponseEntity<List<User>> getAllusers(){
		 List<User> allUser = userService.getAllUser();
		return new ResponseEntity<List<User>>(allUser,HttpStatus.OK);
		 
	 }
	 
	 @PutMapping("{id}")
	 public ResponseEntity<User> updateUser(@PathVariable("id") long userId,@RequestBody User user){
		 user.setId(userId);
		 User updateUser = userService.updateUser(user);
		 return new ResponseEntity<User>(updateUser,HttpStatus.OK);
		 
	 }
	 
	 @DeleteMapping("{id}")
	 public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
		 userService.deleteUser(id);
		 return new ResponseEntity<>("user successfully deleted",HttpStatus.OK);
	 }
	 
	 
}
