package com.deqingbank.cloud.task.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.deqingbank.cloud.task.core.RunnableTask;
import com.deqingbank.cloud.task.entity.Task;
import com.deqingbank.cloud.task.feign.TestServiceFeignClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaskSchedulerService {
	
	@Autowired
	private TestServiceFeignClient client;
	@Autowired
	private ThreadPoolTaskScheduler taskScheduler;
	@Autowired
	private TaskFactory taskFactory;
	
	
	private Map<Long, ScheduledFuture<?>> taskFutureMap = new HashMap<Long,ScheduledFuture<?>>();

	//@Scheduled(fixedRate=60000)
	public void startDownloadTask() {
		for(int i=0;i<20;i++) {
			log.debug(taskScheduler.getThreadNamePrefix() + "{} submited!",i);
			//taskScheduler.execute(new AttendRecordDownloadTask(client,i+""));
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
	public void schedulerTask(Long id,String cron) {
		this.cancelTask(id);
		Trigger trigger = new CronTrigger(cron);
		Runnable runnableTask = taskFactory.buildTask(id);
		ScheduledFuture<?> future = taskScheduler.schedule(runnableTask,trigger);
		taskFutureMap.put(id, future);
	}
	
	/*public void addTask(String serviceUrl,String cron) {
		taskScheduler.execute(new AttendRecordDownloadTask(client, "00"));
	}*/
	
	public boolean cancelTask(Long id) {
		boolean cancelResult;
		ScheduledFuture<?> future = taskFutureMap.get(id);
		if(future==null) {
			cancelResult = false;
		}
		else {
			cancelResult = future.cancel(true);
			taskFutureMap.remove(id);
		}
		return cancelResult;
	}
}
