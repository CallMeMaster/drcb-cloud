package com.deqingbank.cloud.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.deqingbank.cloud.task.test.AttendRecordDownloadTask;

@Service
public class TaskService {
	
	@Autowired
	private TaskExecutor taskExecutor;

	@Scheduled(fixedRate=60000)
	public void startDownloadTask() {
		for(int i=0;i<20;i++) {
			taskExecutor.execute(new AttendRecordDownloadTask("Machine"+i));
		}
	}
	
}
