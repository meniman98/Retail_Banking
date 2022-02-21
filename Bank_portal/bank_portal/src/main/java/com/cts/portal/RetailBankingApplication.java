package com.cts.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RetailBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailBankingApplication.class, args);
//		test comment from Ahmed
//		test comment for a branch
	}

}
