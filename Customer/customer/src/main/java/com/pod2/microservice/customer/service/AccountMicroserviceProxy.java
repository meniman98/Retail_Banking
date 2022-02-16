package com.pod2.microservice.customer.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pod2.microservice.customer.model.AccountCreationStatus;
import com.pod2.microservice.customer.model.AccountSummary;

@FeignClient(name="account-microservice")
public interface AccountMicroserviceProxy {
	@PostMapping("/createAccount")
	public AccountCreationStatus postCreateAccount(Long customerId);
	
	@GetMapping("/getCustomerAccounts")
	public List<AccountSummary> getCustomerAccounts(Long customerId);
}
