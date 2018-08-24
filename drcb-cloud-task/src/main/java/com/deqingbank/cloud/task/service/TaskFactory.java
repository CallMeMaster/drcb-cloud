package com.deqingbank.cloud.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deqingbank.cloud.task.feign.TestServiceFeignClient;
import com.deqingbank.cloud.task.test.AttendRecordDownloadTask;

@Component
public class TaskFactory {

	@Autowired
	private TestServiceFeignClient client;
	
	private TaskFactory() {};
	
	public Runnable buildTask(String serviceUrl) {
		return new AttendRecordDownloadTask(client, serviceUrl);
	}
}
