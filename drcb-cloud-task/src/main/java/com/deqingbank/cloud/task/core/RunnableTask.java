package com.deqingbank.cloud.task.core;

import java.util.Date;

import com.deqingbank.cloud.task.entity.Task;
import com.deqingbank.cloud.task.repository.TaskRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class RunnableTask implements Runnable{
	
	private Task task;
	
	public RunnableTask(Task task) {
		this.task = task;
	}
	
	public Long getId() {
		return this.getTask().getId();
	}
	
	@Override
	public void run() {
		log.debug("task {} run on {}!",this.getId(),new Date());
	}
}
