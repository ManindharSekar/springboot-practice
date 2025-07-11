package com.example.taskmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.taskmanagementsystem.Entity.TaskManagement;
import com.example.taskmanagementsystem.Repository.TaskManagementRepository;

@Service
public class TaskService {
	
	@Autowired
	TaskManagementRepository taskManagementRepo;
	
	

	public ResponseEntity<String> createTask(List<TaskManagement> create) {
		// TODO Auto-generated method stub
		taskManagementRepo.saveAll(create);
		return new ResponseEntity<>("taskCreated",HttpStatus.CREATED);
	}

	public ResponseEntity<Page<TaskManagement>> getAlltasks(int offset, int size, String field) {
		
		return new ResponseEntity(taskManagementRepo.findAll(PageRequest.of(offset, size).withSort(Sort.Direction.ASC,field)),HttpStatus.OK);
	}

	public ResponseEntity<TaskManagement> deleteById(TaskManagement id) {
		// TODO Auto-generated method stub
		
		 taskManagementRepo.delete(id);
		 
		 return new ResponseEntity("task id Deleted",HttpStatus.OK);
		
	}

	public ResponseEntity<TaskManagement> updateTask(Integer id, TaskManagement update) {
		// TODO Auto-generated method stub
		
		TaskManagement findTask = taskManagementRepo.findById(id);
		
		findTask.setCategory(update.getCategory());
		findTask.setCreatedDate(update.getCreatedDate());
		findTask.setDescription(update.getDescription());
		findTask.setDueDate(update.getDueDate());
		findTask.setPriority(update.getPriority());
		findTask.setStatus(update.getStatus());
		findTask.setTitle(update.getTitle());
		
		taskManagementRepo.save(findTask);
		
		return new ResponseEntity("Task id updated",HttpStatus.OK);
	
	}

	public ResponseEntity<TaskManagement> getById(Long id) {
		// TODO Auto-generated method stub
		
		return new ResponseEntity(taskManagementRepo.findById(id),HttpStatus.OK);
	}
	
	
	@Scheduled(cron = "*/30 * * * * *")
	  public void schedulePendingTask() { 
		  List<TaskManagement> tm=taskManagementRepo.findPendingTask("Pending");
		  
		  for(TaskManagement tak:tm) {
		  System.out.println(tak.getId()+"--"+tak.getStatus());
		  }
	  
	  
	  }
	 
	

}
