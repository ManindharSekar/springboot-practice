package com.example.GlobalExceptionHandling.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	// Handle StudentNotFoundException
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handleStudentNotFound(StudentNotFoundException ex){
		Map<String,Object> error=new HashMap<String,Object>();
		error.put("timestamp", LocalDateTime.now());
		error.put("status", HttpStatus.NOT_FOUND.value());
		error.put("error","Not Found");
		error.put("message", ex.getMessage());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		
	}
	
	// Handle all other exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,Object>> handleGeneral(Exception ex){
		Map<String,Object> error=new HashMap<String,Object>();
		error.put("timestamp", LocalDateTime.now());
		error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.put("error", "Internal Server Error");
		error.put("message", ex.getMessage());
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
