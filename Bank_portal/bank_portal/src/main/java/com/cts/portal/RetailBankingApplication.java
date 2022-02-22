package com.cts.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.cts.portal")
@EnableEurekaServer
public class RetailBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailBankingApplication.class, args);
	}

}
