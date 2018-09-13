package com.deqingbank.cloud.task.core;

import java.util.Date;

import com.deqingbank.cloud.task.entity.Task;
import com.deqingbank.cloud.task.entity.TaskState;
import com.deqingbank.cloud.task.service.TaskService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class RunnableTask implements Runnable{
	
	private Task task;
	private TaskService taskService;
	
	public RunnableTask(Task task,TaskService taskService) {
		this.task = task;
		this.taskService = taskService;
	}
	
	public Long getId() {
		return this.getTask().getId();
	}
	
	@Override
	public void run() {
		//taskService.updateState(this.getTask().getId(),TaskState.PROCESSING);
		log.debug("task {} run on {}!",this.getId(),new Date());
		//taskService.updateState(this.getId(),TaskState.PENDING);
	}
}
