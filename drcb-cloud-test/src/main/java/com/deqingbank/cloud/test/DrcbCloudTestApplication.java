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
	private static Long SLEEP_LENGTH = 0L;
	public static void main(String[] args) {
		SpringApplication.run(DrcbCloudTestApplication.class, args);
	}
	
	/**
	 * @param ipAddr
	 */
	@ApiOperation(value="download service",notes="download mock service!")
	@GetMapping("/ip/{ipAddr}")
	public String download(@PathVariable Long ipAddr) {
		logger.debug("{} start to download!",ipAddr);
		try {
			Thread.sleep(SLEEP_LENGTH);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	@ApiOperation(value="sleep length",notes="修改睡眠时间！")
	@GetMapping("/sleep/{sleepLength}")
	public String changeSleepLength(@PathVariable Long sleepLength) {
		SLEEP_LENGTH = sleepLength;
		logger.debug("SLEEP_LENGTH:{}",SLEEP_LENGTH);
		return "睡眠时间修改为："+SLEEP_LENGTH;
	}
}
