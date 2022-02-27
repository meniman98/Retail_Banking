package com.cts.Account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cts.Account.Utils;
import com.cts.Account.model.Account;
import com.cts.Account.model.AccountCreationStatus;
import com.cts.Account.service.AccountServiceImpl;
import com.cts.Account.service.SecurityService;

@RestController
public class AccountController {

	@Autowired
	AccountServiceImpl accountService;

	@Autowired
	private SecurityService securityService;

	// this method works very well
	@PostMapping(Utils.CREATE_ACCOUNT)
	public AccountCreationStatus createAccount(@PathVariable Long customerId,
			@RequestHeader(name = "Authorization", required = false) String bearerToken) {
		if (securityService.isMember(bearerToken)) {
			return accountService.createAccount(customerId);
		}
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only members can access this resource.");
	}

	// this method works great
	@GetMapping(Utils.GET_CUSTOMER_ACCOUNTS)
	public List<Account> getCustomerAccounts(@PathVariable Long customerId,
			@RequestHeader(name = "Authorization", required = false) String bearerToken) {
		if (securityService.isMember(bearerToken)) {
			return accountService.getCustomerAccounts(customerId);
		}
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only members can access this resource.");
	}

	// This method works great
	@GetMapping(Utils.GET_ACCOUNT)
	public Account getAccount(@PathVariable Long accountId, @RequestHeader(name = "Authorization", required = false) String bearerToken) {
		if (securityService.isMember(bearerToken)) {
			return accountService.getAccount(accountId);
		}
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only members can access this resource.");
	}

	@GetMapping(Utils.GET_ACCOUNT_BY_NUMBER)
	public Account getAccountNumber(@PathVariable String accountNo, @RequestHeader(name = "Authorization", required = false) String bearerToken) {
		if (securityService.isMember(bearerToken)) {
			return accountService.getAccountByNo(accountNo);
		}
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only members can access this resource.");
	}

}
