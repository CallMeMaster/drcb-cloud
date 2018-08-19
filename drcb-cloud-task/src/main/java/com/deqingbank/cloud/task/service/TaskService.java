package com.deqingbank.cloud.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deqingbank.cloud.task.entity.Task;
import com.deqingbank.cloud.task.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public void add(Task task) {
		taskRepository.save(task);
	}
	
}
