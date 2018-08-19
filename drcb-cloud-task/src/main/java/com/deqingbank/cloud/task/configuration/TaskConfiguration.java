package com.deqingbank.cloud.task.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class TaskConfiguration {

	@Bean("taskPool")
	public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(5);
		threadPoolTaskExecutor.setMaxPoolSize(10);
		threadPoolTaskExecutor.setQueueCapacity(25);
		threadPoolTaskExecutor.setThreadNamePrefix("AttendRecordDownloadTask-");
		threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		return threadPoolTaskExecutor;
	}
	
	@Bean("taskScheduler")
	public ThreadPoolTaskScheduler getTaskScheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(5);
		taskScheduler.setBeanName("SchedulerTask");
		return taskScheduler;
	}
}
