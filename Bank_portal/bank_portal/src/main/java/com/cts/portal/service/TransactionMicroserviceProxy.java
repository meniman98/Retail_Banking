package com.cts.portal.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.portal.dto.Transaction;
import com.cts.portal.dto.TransactionStatus;

@FeignClient(name = "${transaction.microservice.name}")
public interface TransactionMicroserviceProxy {
	@PostMapping(value = "/deposit/{accountID}")
	public TransactionStatus deposit(@PathVariable Long accountID, @RequestParam double amount);

	@PostMapping(value = "/withdraw/{accountID}")
	public TransactionStatus withdraw(@PathVariable Long accountID, @RequestParam double amount);

	@PostMapping(value = "/transfer/{accountID}")
	public TransactionStatus transfer(@PathVariable Long sourceAccountID, @RequestParam Long destAccountID, @RequestParam double amount);

	@GetMapping(value = "/getTransaction/{customerID}")
	public List<Transaction> getTransaction(@PathVariable Long customerID);
}
