package com.example.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizapp.entity.QuestionWrapper;
import com.example.quizapp.entity.Questions;
import com.example.quizapp.entity.Quiz;
import com.example.quizapp.entity.Response;
import com.example.quizapp.repository.QuestionsDao;
import com.example.quizapp.repository.QuizDao;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionsDao questiondao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		// TODO Auto-generated method stub
		List<Questions> questions=questiondao.findQuizByCategory(category,numQ,title);
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		
		quizDao.save(quiz);
		
		return new ResponseEntity<>("success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		// TODO Auto-generated method stub
		
		Optional<Quiz> quiz = quizDao.findById(id);
		List<Questions> questions = quiz.get().getQuestions();
		ArrayList<QuestionWrapper> questionForUser = new ArrayList<>();
		
		for(Questions q:questions) {
			
			QuestionWrapper questionWrapper = new QuestionWrapper(q.getId(),q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			questionForUser.add(questionWrapper);
		}
		return new ResponseEntity(questionForUser,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculation(Integer id, List<Response> responses) {
		// TODO Auto-generated method stub
		Quiz quiz = quizDao.findById(id).get();
		List<Questions> questions = quiz.getQuestions();
		int right=0;
		int i=0;
		for(Response response:responses) {
			if(response.getResponse().equals(questions.get(i).getRightAnswer())) {
				right++;
			}
			i++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

}
