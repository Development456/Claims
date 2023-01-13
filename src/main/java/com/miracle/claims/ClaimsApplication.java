package com.miracle.claims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableEurekaClient
@EnableDiscoveryClient
public class ClaimsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaimsApplication.class, args);
		
	}

}
