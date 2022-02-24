package com.pod2.microservice.customer.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pod2.microservice.customer.model.AccountCreationStatus;
import com.pod2.microservice.customer.model.AccountSummary;

@FeignClient(name="${account.microservice.name}")
public interface AccountMicroserviceProxy {
	@PostMapping("${account.microservice.create.path}")
	public AccountCreationStatus postCreateAccount(@PathVariable Long customerId);
	
	@GetMapping("${account.microservice.getDetails.path}")
	public List<AccountSummary> getCustomerAccounts(@PathVariable Long customerId);
}
