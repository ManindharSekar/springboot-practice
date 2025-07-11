package com.example.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizapp.entity.Questions;
import com.example.quizapp.repository.QuestionsDao;

@Service
public class QuestionsService {
	
	@Autowired
	QuestionsDao questionsDao;

	public ResponseEntity<String> saveQuestion(Questions questions) {
		// TODO Auto-generated method stub
		try {
		questionsDao.save(questions);
		return new ResponseEntity<>("success",HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity("failure",HttpStatus.BAD_REQUEST);
		}
		
	}

	public ResponseEntity<List<Questions>> getAllQuestions() {
		// TODO Auto-generated method stub
		try {
		return new ResponseEntity(questionsDao.findAll(),HttpStatus.OK);
	}catch(Exception e) {
		return new ResponseEntity(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	}

	public ResponseEntity<List<Questions>> getqustCategory(String category) {
		try {
		// TODO Auto-generated method stub
		return new ResponseEntity(questionsDao.findByCategory(category),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	

}
