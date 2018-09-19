package com.deqingbank.cloud.task.core;

import java.util.Date;

import com.deqingbank.cloud.task.ServiceLocator;
import com.deqingbank.cloud.task.entity.Task;
import com.deqingbank.cloud.task.entity.TaskState;
import com.deqingbank.cloud.task.service.TaskService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class RunnableTask implements Runnable {
	
	
	
	private Task task;
	
	public RunnableTask(Task task) {
		this.task = task;
	}
	
	public Long getId() {
		return this.getTask().getId();
	}
	
	@Override
	public void run() {
		getTaskService().updateState(this.getId(), TaskState.PROCESSING);
		log.debug("task {} run on {}!",this.getId(),new Date());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			log.debug("sdfsdfadf");
			getTaskService().updateState(this.getId(), TaskState.ERROR);
			e.printStackTrace();
		}
		getTaskService().updateState(this.getId(), TaskState.PENDING);
	}
	
	private TaskService getTaskService() {
		return (TaskService)ServiceLocator.getService("taskService");
	}
}
