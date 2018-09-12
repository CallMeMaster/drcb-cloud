package com.deqingbank.cloud.task.core;

import java.util.Date;

import com.deqingbank.cloud.task.feign.TestServiceFeignClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunnableTask implements Runnable{
	
	private TestServiceFeignClient testServiceFeignClient;
	private Long id;
	
	
	public RunnableTask(TestServiceFeignClient client,Long taskId) {
		this.testServiceFeignClient = client;
		this.id = taskId;
	}
	
	@Override
	public void run() {
		//String result = testServiceFeignClient.test(ipaddr);
		//log.debug("{}======{}!",ipaddr,result);
		log.debug("task {} run on {}!",this.id,new Date());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RunnableTask other = (RunnableTask) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
