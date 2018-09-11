package com.deqingbank.cloud.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.deqingbank.cloud.task.entity.Task;
import com.deqingbank.cloud.task.service.TaskService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Api
@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@RequestMapping("/delete/{id}")
	public boolean addTask(@PathVariable("id") Long id) {
		taskService.delete(id);
		return true;
	}
	
	@RequestMapping("/list")
	public List<Task> getTaskList() {
		log.debug("TaskLength:{}",taskService.list().size());
		return taskService.list();
	}
	
	@RequestMapping("/add")
	public boolean addTask(@RequestParam String name,@RequestParam String cron,@RequestParam String url) {
		Task task = new Task();
		task.setName(name);
		task.setUrl(url);
		task.setCron(cron);
		taskService.add(task);
		return true;
	}
	
	@RequestMapping("/update/{id}")
	public boolean addTask(@PathVariable Long id,@RequestParam String name,@RequestParam String cron,@RequestParam String url) {
		Task task = new Task();
		task.setId(id);
		task.setName(name);
		task.setUrl(url);
		task.setCron(cron);
		taskService.add(task);
		return true;
	}
	
}
