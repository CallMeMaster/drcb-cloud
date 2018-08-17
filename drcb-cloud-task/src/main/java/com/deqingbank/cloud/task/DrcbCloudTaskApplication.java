package com.deqingbank.cloud.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DrcbCloudTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrcbCloudTaskApplication.class, args);
	}
}


