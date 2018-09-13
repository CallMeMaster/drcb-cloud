package com.deqingbank.cloud.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deqingbank.cloud.task.core.RunnableTask;
import com.deqingbank.cloud.task.feign.TestServiceFeignClient;

@Component
public class TaskFactory {

	@Autowired
	private TestServiceFeignClient client;
	
	private TaskFactory() {};
	
	/*public Runnable buildTask(Task,TaskService taskService) {
		return new RunnableTask(client, id, taskService);
	}*/
}
