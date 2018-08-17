package com.deqingbank.cloud.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class DrcbCloudAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrcbCloudAdminApplication.class, args);
	}
}
