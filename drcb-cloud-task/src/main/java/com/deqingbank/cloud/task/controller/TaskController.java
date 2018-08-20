package com.deqingbank.cloud.task.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deqingbank.cloud.task.service.TaskSchedulerService;
import com.deqingbank.cloud.task.service.TaskService;


@RestController
@RequestMapping("/task")
public class TaskController {
	
	private Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	private TaskService taskService;
	@Autowired
	private TaskSchedulerService schedulerService;
	
	@RequestMapping("/download")
	public String startDownload() {
		logger.debug("download action!");
		schedulerService.startDownloadTask();
		return "success";
	}
	
	@RequestMapping("/info")
	public String taskInfo() {
		return null;
		//return taskService.getTaskInfo();
	}
	
	@RequestMapping("/addTask")
	public void addTask(@RequestParam("jobClassName")String jobClassName,
			@RequestParam("jobGroupName")String jobGroupName,
			@RequestParam("cronExpression")String cronExpression) {
	//	taskService.addTask(jobClassName,jobGroupName,cronExpression);
	}
	
}
