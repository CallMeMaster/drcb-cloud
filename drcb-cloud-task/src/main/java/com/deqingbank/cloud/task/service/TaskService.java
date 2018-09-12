package com.deqingbank.cloud.task.service;

import java.util.List;
import java.util.Optional;

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
		//init state is cancel
		task.setState(TaskState.CANCEL);
		//save task to db
		repository.save(task);
	}
	
	
	public void delete(Long id) {
		//cancel in the task pool
		scheduler.cancelTask(id);
		//delete from database
		repository.deleteById(id);
	}
	
	public void cancel(Long id) {
		//pool
		scheduler.cancelTask(id);
		//db
		repository.setState(id,TaskState.CANCEL);
	}
	
	public boolean enable(Long id) {
		Optional<Task> opt = repository.findById(id);
		if(opt.isPresent()) {
			Task task = opt.get();
			scheduler.schedulerTask(id, task.getCron());
			//db
			repository.setState(id,TaskState.PENDING);
			return true;
		}
		return false;
	}
	
	public void update(Task task) {
		//repository.update(task.getId(),task.getName(),task.getCron(),task.getUrl());
	}
	
}
