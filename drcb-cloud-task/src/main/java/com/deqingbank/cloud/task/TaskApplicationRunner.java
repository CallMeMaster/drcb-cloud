package com.deqingbank.cloud.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.deqingbank.cloud.task.service.TaskService;


@Component
public class TaskApplicationRunner implements ApplicationRunner{
	
 	@Autowired
 	private TaskService taskService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		taskService.loadLask();
	}
}
