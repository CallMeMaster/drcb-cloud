package com.deqingbank.cloud.task.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import com.deqingbank.cloud.task.feign.TestServiceFeignClient;
import com.deqingbank.cloud.task.test.AttendRecordDownloadTask;

@Service
public class TaskSchedulerService {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskSchedulerService.class);

	@Autowired
	private TestServiceFeignClient client;
	@Autowired
	private ThreadPoolTaskScheduler taskScheduler;

	//@Scheduled(fixedRate=60000)
	public void startDownloadTask() {
		for(int i=0;i<20;i++) {
			logger.debug(taskScheduler.getThreadNamePrefix() + "{} submited!",i);
			taskScheduler.execute(new AttendRecordDownloadTask(client,i+""));
		}
	}
	
	public String getTaskInfo() {
		return "active count:"+taskScheduler.getActiveCount()+ "\n"+
				"pool size:"+taskScheduler.getPoolSize()+"\n" +
				"thread name prefix:"+taskScheduler.getThreadNamePrefix()+"\n"+
				"thread priority:"+taskScheduler.getThreadPriority();
	}

	public void addTask(String jobClassName, String jobGroupName, String cronExpression) {
	}
}
