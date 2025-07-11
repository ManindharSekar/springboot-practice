package com.example.quizapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.quizapp.entity.Questions;

@Repository

public interface QuestionsDao extends JpaRepository<Questions,Long> {
	
	 List<Questions> findByCategory(String category);
	 
	 @Query(value = "SELECT * FROM questions q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
	List<Questions> findQuizByCategory(String category, int numQ, String title);

}
