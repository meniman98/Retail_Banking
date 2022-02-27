package com.cts.Account.controller;


import com.cts.Account.Utils;
import com.cts.Account.model.TransactionStatus;
import com.cts.Account.service.SecurityService;
import com.cts.Account.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionService;
    
    @Autowired
	private SecurityService securityService;

//    Tested, works great
    @PostMapping(Utils.DEPOSIT)
    public TransactionStatus deposit(@PathVariable Long accountId, @PathVariable double amount, @RequestHeader(name = "Authorization", required = false) String bearerToken) {
    	if (this.securityService.isMember(bearerToken)) {
    		return transactionService.deposit(accountId, amount);
    	}
    	throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only members can access this resource.");
    }

//    Tested, works great
    @PostMapping(Utils.WITHDRAW)
    public TransactionStatus withdraw(@PathVariable Long accountId, @PathVariable double amount, @RequestHeader(name = "Authorization", required = false) String bearerToken) {
    	if (this.securityService.isMember(bearerToken)) {
    		return transactionService.withdraw(accountId, amount);
    	}
    	throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only members can access this resource.");
    }
}
