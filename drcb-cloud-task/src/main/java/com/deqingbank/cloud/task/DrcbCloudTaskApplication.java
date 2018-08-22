package com.deqingbank.cloud.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class DrcbCloudTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrcbCloudTaskApplication.class, args);
	}
}


