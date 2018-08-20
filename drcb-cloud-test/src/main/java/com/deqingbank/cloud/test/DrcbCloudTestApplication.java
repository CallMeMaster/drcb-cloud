package com.deqingbank.cloud.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
@RestController
@RequestMapping("/zkt")
public class DrcbCloudTestApplication {

	private Logger logger = LoggerFactory.getLogger(DrcbCloudTestApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(DrcbCloudTestApplication.class, args);
	}
	
	@RequestMapping("/{ipAddr}")
	public void download(@PathVariable String ipAddr) {
		logger.debug("{} start to download!",ipAddr);
	}
	
	/*@RequestMapping("/test")
	public void test() {
		logger.debug("test start to download!");
	}*/
	
}
