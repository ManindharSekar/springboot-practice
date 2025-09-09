package com.example.GlobalExceptionHandling.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GlobalExceptionHandling.exception.StudentNotFoundException;
import com.example.GlobalExceptionHandling.model.Student;

@RestController
@RequestMapping("student")
public class StudentController {
	
	List<Student> students=new ArrayList<Student>();
	
	@GetMapping("/{id}")
	public  Student getStudentById(@PathVariable int id) {
		
		return students.stream()
				.filter(s->s.getId()==id)
				.findFirst()
				.orElseThrow(()-> new StudentNotFoundException("Student with ID " + id + " not found"));
	}

}
