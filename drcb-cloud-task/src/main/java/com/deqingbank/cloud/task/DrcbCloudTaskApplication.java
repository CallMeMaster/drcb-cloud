package com.deqingbank.cloud.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableScheduling
@EnableAdminServer
public class DrcbCloudTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrcbCloudTaskApplication.class, args);
	}
}
