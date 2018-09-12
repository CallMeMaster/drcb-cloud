package com.deqingbank.cloud.task.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledFuture;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.deqingbank.cloud.task.core.RunnableTask;
import com.deqingbank.cloud.task.entity.Task;
import com.deqingbank.cloud.task.entity.TaskState;
import com.deqingbank.cloud.task.feign.TestServiceFeignClient;
import com.deqingbank.cloud.task.repository.TaskRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaskService {
	
	@Autowired
	private TestServiceFeignClient client;
	@Autowired
	private ThreadPoolTaskScheduler taskScheduler;
	@Autowired
	private TaskFactory taskFactory;
	@Autowired
	private TaskRepository repository;
	
	
	private Map<Long, ScheduledFuture<?>> taskFutureMap = new HashMap<Long,ScheduledFuture<?>>();

	//@Scheduled(fixedRate=60000)
	private void startDownloadTask() {
		for(int i=0;i<20;i++) {
			log.debug(taskScheduler.getThreadNamePrefix() + "{} submited!",i);
			//taskScheduler.execute(new AttendRecordDownloadTask(client,i+""));
		}
	}
	
	private String getTaskInfo() {
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
	
	private boolean cancelTask(Long id) {
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
	
	public void add(Task task) {
		//init state is cancel
		task.setState(TaskState.CANCEL);
		//save task to db
		repository.save(task);
	}
	
	public List<Task> list(){
		return repository.findAll();
	}
	
	@Transactional
	public boolean enable(Long id) {
		Optional<Task> opt = repository.findById(id);
		if(opt.isPresent()) {
			Task task = opt.get();
			this.schedulerTask(id, task.getCron());
			//db
			repository.setState(id,TaskState.PENDING);
			return true;
		}
		return false;
	}
	
	@Transactional
	public void cancel(Long id) {
		//pool
		this.cancelTask(id);
		//db
		repository.setState(id,TaskState.CANCEL);
	}
	
	public void delete(Long id) {
		//cancel in the task pool
		this.cancelTask(id);
		//delete from database
		repository.deleteById(id);
	}
	
	public void update(Task task) {
		//repository.update(task.getId(),task.getName(),task.getCron(),task.getUrl());
	}
}
