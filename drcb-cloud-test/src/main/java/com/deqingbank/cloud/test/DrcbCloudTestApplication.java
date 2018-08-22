package com.deqingbank.cloud.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@SpringBootApplication
@EnableFeignClients
@RestController
@RequestMapping("/zkt")
@Api
public class DrcbCloudTestApplication {

	private Logger logger = LoggerFactory.getLogger(DrcbCloudTestApplication.class);
	private static int sleepThread = 0;
	public static void main(String[] args) {
		SpringApplication.run(DrcbCloudTestApplication.class, args);
	}
	
	/**
	 * if ip addr is 00,then it goes to sleep.
	 * @param ipAddr
	 */
	@ApiOperation(value="download service",notes="download mock service!")
	@GetMapping("/{ipAddr}")
	public String download(@PathVariable Long ipAddr) {
		logger.debug("{} start to download!",ipAddr);
		sleepThread ++;
		logger.debug("Failed service {}",sleepThread);
		try {
			Thread.sleep(ipAddr);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "success";
	}
}
