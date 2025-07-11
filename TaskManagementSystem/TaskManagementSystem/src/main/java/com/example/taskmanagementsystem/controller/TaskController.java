package com.example.taskmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanagementsystem.Entity.TaskManagement;
import com.example.taskmanagementsystem.service.TaskService;



@RestController
@RequestMapping("TaskManagement")
public class TaskController {
	

	TaskService taskService;
	
	@PostMapping("create")
	public String createTask(@RequestBody List<TaskManagement> create){
		 taskService.createTask(create);
		 return "Task created Successfully";
		
	}
	
	@GetMapping("getAll/{offset}/{size}/{field}")
	public ResponseEntity<Page<TaskManagement>> getAllTask(@PathVariable int offset,@PathVariable int size,@PathVariable String field){
		return taskService.getAlltasks(offset,size,field);
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<TaskManagement> getById(@PathVariable Long id){

		return taskService.getById(id);
	}
	
	@DeleteMapping("deleteById/{id}")
	public String deleteTaskById(@PathVariable TaskManagement id) {
		
		taskService.deleteById(id);
		
		return "Task id Deleted Successfully";
	}
	
	@PutMapping("updateById/{id}")
	public String updateTaskById(@PathVariable Integer id,@RequestBody TaskManagement update) {
		taskService.updateTask(id,update);
		return " Task id Updated Successfully";
	}

	
}

