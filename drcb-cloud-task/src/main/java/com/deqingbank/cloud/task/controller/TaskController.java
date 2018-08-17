package com.deqingbank.cloud.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deqingbank.cloud.task.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@RequestMapping("/download")
	public String startDownload() {
		taskService.startDownloadTask();
		return "success";
	}
	
}
