package com.deqingbank.cloud.task.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.deqingbank.cloud.task.entity.Task;
import com.deqingbank.cloud.task.feign.TestServiceFeignClient;
import com.deqingbank.cloud.task.test.AttendRecordDownloadTask;

@Service
public class TaskSchedulerService {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskSchedulerService.class);

	@Autowired
	private TestServiceFeignClient client;
	@Autowired
	private ThreadPoolTaskScheduler taskScheduler;
	@Autowired
	private TaskFactory taskFactory;

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

	/**
	 * 
	 * @param serviceUrl
	 * @param cronExpression
	 */
	public void schedulerTask(Task task) {
		Trigger trigger = new CronTrigger(task.getCron());
		taskScheduler.schedule(taskFactory.buildTask(task.getUrl()),trigger);
	}
	
	public void addTask(String serviceUrl,String cron) {
		taskScheduler.execute(new AttendRecordDownloadTask(client, "00"));
	}
}
