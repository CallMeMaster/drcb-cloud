package com.deqingbank.cloud.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DrcbCloudDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrcbCloudDiscoveryApplication.class, args);
	}
}
