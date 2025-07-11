package com.example.SpringSecExample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {
	
	private List<Student> s=new ArrayList<>(List.of(new Student(1,"Mani",75),new Student(2,"ilys",80)));
	
	@GetMapping("/students")
	public List<Student> getStudent(){
		return s;
	}
	
	@PostMapping("/students")
	public Student createStudent(@RequestBody Student students ) {
		s.add(students);
		return students;
	}
	
	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest request) {
		
		return (CsrfToken) request.getAttribute("_csrf");
	}
	

}
