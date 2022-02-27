package com.cts.Account.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import feign.Headers;

@FeignClient(name="${security.microservice.name}")
public interface SecurityRoleJwtMicroserviceProxy {
	
	String AUTH_TOKEN = "Authorization";
	
	@GetMapping("${security.microservice.employee.access}")
	@Headers("Content-Type: application/json")
	public boolean employeePing(@RequestHeader(AUTH_TOKEN) String bearerToken);
	
	@GetMapping("${security.microservice.customer.access}")
	@Headers("Content-Type: application/json")
	public boolean customerPing(@RequestHeader(AUTH_TOKEN) String bearerToken);
	
}
