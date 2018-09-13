package com.deqingbank.cloud.task.service;

import java.util.Collections;
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
import org.springframework.util.CollectionUtils;

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
	
	/**
	 * load task from db when the app start!
	 * @param id
	 * @param cron
	 */
	public void loadLask() {
		log.debug("task application runner start!");
		List<Task> tasks = this.list();
		log.debug("task init:{}",tasks.size());
		if(CollectionUtils.isEmpty(tasks)) {
			return;
		}
		tasks.stream().filter(task->task.getState()!=TaskState.CANCEL).forEach(task->{
			updateMapAndPool(task);
		});
		log.debug("init task:{}",tasks.size());
	}

	private void updateMapAndPool(Task task) {
		ScheduledFuture<?> oldFuture = taskFutureMap.get(task.getId());
		if(oldFuture!=null) {
			oldFuture.cancel(true);
		}
		Runnable runnableTask = new RunnableTask(task, this);
		Trigger trigger = new CronTrigger(task.getCron());
		ScheduledFuture<?> future = taskScheduler.schedule(runnableTask,trigger);
		taskFutureMap.put(task.getId(), future);
	}
	
	/*public void addTask(String serviceUrl,String cron) {
		taskScheduler.execute(new AttendRecordDownloadTask(client, "00"));
	}*/
	
	
	/**
	 * add Task
	 * @param task
	 */
	@Transactional
	public void add(Task task) {
		//init state is cancel
		task.setState(TaskState.CANCEL);
		//save task to db
		repository.save(task);
	}
	
	/**
	 * update task
	 * @param task
	 */
	@Transactional
	public void update(Task task) {
		if(this.taskFutureMap.containsKey(task.getId())) {
			updateMapAndPool(task);
		}
		repository.update(task.getId(),task.getName(),task.getCron(),task.getUrl());
	}
	
	/**
	 * list task
	 * @return
	 */
	public List<Task> list(){
		return repository.findAll();
	}
	
	/**
	 * enable task
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean enable(Long id) {
		Task task = repository.getOne(id);
		log.debug("task is null,{}",task==null);
		if(task==null) {
			return false;
		}
		this.updateMapAndPool(task);
		//db
		repository.setState(id,TaskState.PENDING);
		return false;
	}
	
	/**
	 * cancel task
	 * @param id
	 */
	@Transactional
	public void disableTask(Long id) {
		//pool
		this.removeTaskFromMapAndPool(id);
		//db
		repository.setState(id,TaskState.CANCEL);
	}
	
	
	/**
	 * delete task
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		//cancel in the task pool
		this.removeTaskFromMapAndPool(id);
		//delete from database
		repository.deleteById(id);
	}
	
	/**
	 * remove the task from map and the pool
	 * @param id
	 * @return
	 */
	private boolean removeTaskFromMapAndPool(Long id) {
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
	
	@Transactional
	public void updateState(Long id,TaskState taskState) {
		repository.setState(id,taskState);
	}
}
