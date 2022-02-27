package com.cts.portal.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.portal.dto.Transaction;
import com.cts.portal.dto.TransactionStatus;

@FeignClient(name = "${transaction.microservice.name}")
public interface TransactionMicroserviceProxy {
	@PostMapping(value = "/deposit/{accountId}/{amount}")
	public TransactionStatus deposit(@PathVariable Long accountId, @PathVariable double amount, @RequestHeader(name = "Authorization", required = false) String bearerToken);

	@PostMapping(value = "/withdraw/{accountId}/{amount}")
	public TransactionStatus withdraw(@PathVariable Long accountId, @PathVariable double amount, @RequestHeader(name = "Authorization", required = false) String bearerToken);

	@PostMapping(value = "/transfer/{sourceAccountId}/{destAccountID}/{amount}")
	public TransactionStatus transfer(@PathVariable Long sourceAccountId, @PathVariable Long destAccountID, @PathVariable double amount, @RequestHeader(name = "Authorization", required = false) String bearerToken);

	@GetMapping(value = "/getTransaction/{customerID}")
	public List<Transaction> getTransaction(@PathVariable Long customerID, @RequestHeader(name = "Authorization", required = false) String bearerToken);
}
