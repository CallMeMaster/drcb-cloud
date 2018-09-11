package com.deqingbank.cloud.task.test;

import com.deqingbank.cloud.task.feign.TestServiceFeignClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AttendRecordDownloadTask implements Runnable{
	
	private TestServiceFeignClient testServiceFeignClient;
	private String ipaddr;
	
	public AttendRecordDownloadTask(TestServiceFeignClient client,String ipaddr) {
		this.testServiceFeignClient = client;
		this.ipaddr = ipaddr;
	}
	
	@Override
	public void run() {
		if(ipaddr.equals("10") || ipaddr.equals("14")) {
			try {
				log.debug("{}======sleep",ipaddr);
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String result = testServiceFeignClient.test(ipaddr);
		log.debug("{}======{}!",ipaddr,result);
	}
}
