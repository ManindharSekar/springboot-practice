package com.example.quizapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizapp.entity.Questions;
import com.example.quizapp.service.QuestionsService;

@RestController
@RequestMapping("questions")
public class QuestionController {
	
	@Autowired
	QuestionsService questionsService;
	
	@PostMapping("add")
	public ResponseEntity<String> addQuestions(@RequestBody Questions questions) {
		 return questionsService.saveQuestion(questions);
		
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Questions>> getAllQuestions(){
	
		return questionsService.getAllQuestions();
		
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Questions>> gatCategory(@PathVariable String category){
		return questionsService.getqustCategory(category);
		
	}

}
