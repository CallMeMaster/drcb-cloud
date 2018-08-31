package com.deqingbank.cloud.task.controller;

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

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/task")
public class TaskController {
	
	private Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	private TestServiceFeignClient client;
	
	@Autowired 
	private TaskSchedulerService scheduler; 
	
	@GetMapping("/download")
	public String startDownload() {
		logger.debug("download action!");
		//schedulerService.startDownloadTask();
		return "success";
	}
	
	@GetMapping("/info")
	public String taskInfo() {
		return null;
		//return taskService.getTaskInfo();
	}
	
	@GetMapping("/addTask/{ipaddr}")
	public String addTask(@PathVariable("ipaddr") String ipaddr) {
		//scheduler.
		return client.test(ipaddr);
	}
	
	@PostMapping("/")
	public String saveTask(@RequestBody Task task) {
		logger.debug(task.toString());
		return "1111";
	}
	
}
