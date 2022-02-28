package com.cts.portal.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.portal.dto.AccountSummary;
import com.cts.portal.dto.Statement;

@FeignClient(name = "${account.microservice.name}")
public interface AccountMicroserviceProxy {
	@GetMapping("${account.microservice.getaccountbyno}")
	public AccountSummary getAccountNumber(@PathVariable String accountNo, @RequestHeader(name = "Authorization", required = false) String bearerToken);

	@GetMapping("${account.microservice.getaccountstatements}")
	List<Statement> getStatementList(@PathVariable Long accountId,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate, @RequestHeader(name = "Authorization", required = false) String bearerToken);
}
