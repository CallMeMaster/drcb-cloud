package com.deqingbank.cloud.task.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deqingbank.cloud.task.entity.Task;
import com.deqingbank.cloud.task.feign.TestServiceFeignClient;
import com.deqingbank.cloud.task.service.TaskSchedulerService;
import com.deqingbank.cloud.task.service.TaskService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Api
@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {
	
	@Autowired
	private TestServiceFeignClient client;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired 
	private TaskSchedulerService scheduler; 
	
	@GetMapping("/download")
	public String startDownload() {
		log.debug("download action!");
		//schedulerService.startDownloadTask();
		return "success";
	}
	
	@GetMapping("/info")
	public String taskInfo() {
		return null;
		//return taskService.getTaskInfo();
	}
	
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
