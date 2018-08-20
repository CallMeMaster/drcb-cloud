package com.deqingbank.cloud.task.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deqingbank.cloud.task.feign.TestServiceFeignClient;

public class AttendRecordDownloadTask implements Runnable{
	
	private static final Logger logger = LoggerFactory.getLogger(AttendRecordDownloadTask.class);

	
	private TestServiceFeignClient testService;
	private String ipaddr;
	
	public AttendRecordDownloadTask(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	
	@Override
	public void run() {
		if(ipaddr.equals("10") || ipaddr.equals("14")) {
			try {
				logger.debug("{}======sleep",ipaddr);
				testService.download(ipaddr);
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
