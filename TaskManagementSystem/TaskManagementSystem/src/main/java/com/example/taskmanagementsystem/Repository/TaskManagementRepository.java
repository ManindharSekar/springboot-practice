package com.example.taskmanagementsystem.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;

import com.example.taskmanagementsystem.Entity.TaskManagement;

@Repository
public interface TaskManagementRepository extends JpaRepository<TaskManagement,Long> {

	TaskManagement findById(Integer id);
	
	@Query(value = "select * from task_management where status=:status",nativeQuery =true)
	List<TaskManagement> findPendingTask(String status);

	


	

}
