package com.deqingbank.cloud.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deqingbank.cloud.task.entity.Task;
import com.deqingbank.cloud.task.entity.TaskState;
import com.deqingbank.cloud.task.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository repository;
	
	@Autowired
	private TaskSchedulerService scheduler;
	
	public List<Task> list(){
		return repository.findAll();
	}
	
	public void add(Task task) {
		task.setState(TaskState.PENDING);
		scheduler.schedulerTask(task);
		repository.save(task);
	}
	
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public void update(Task task) {
		scheduler.schedulerTask(task);
		repository.update(task.getId(),task.getName(),task.getCron(),task.getUrl());
	}
	
}
