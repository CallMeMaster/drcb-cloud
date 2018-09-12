package com.deqingbank.cloud.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.deqingbank.cloud.task.entity.Task;
import com.deqingbank.cloud.task.service.TaskSchedulerService;
import com.deqingbank.cloud.task.service.TaskService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TaskApplicationRunner implements ApplicationRunner{
	
 	@Autowired
 	private TaskService taskService;
 	@Autowired
 	private TaskSchedulerService taskSchedulerService;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		log.debug("task application runner start!");
		List<Task> tasks = taskService.list();
		log.debug("task init:{}",tasks.size());
		tasks.forEach((task)->taskSchedulerService.schedulerTask(task));
	}

}