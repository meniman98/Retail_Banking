package com.cts.Account.controller;


import com.cts.Account.Utils;
import com.cts.Account.model.Account;
import com.cts.Account.model.AccountCreationStatus;
import com.cts.Account.model.Customer;
import com.cts.Account.repo.AccountRepo;
import com.cts.Account.repo.CustomerRepo;
import com.cts.Account.repo.StatementRepo;
import com.cts.Account.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
public class AccountController {

    @Autowired
    AccountServiceImpl accountService;

    //    this method works very well
    @PostMapping(Utils.CREATE_ACCOUNT)
    public AccountCreationStatus createAccount(@PathVariable Long customerId) {
        return accountService.createAccount(customerId);
    }

    //    this method works great
    @GetMapping(Utils.GET_CUSTOMER_ACCOUNTS)
    public Set<Account> getCustomerAccounts(@PathVariable Long customerId) {
        return accountService.getCustomerAccounts(customerId);
    }

    //    This method works great
    @GetMapping(Utils.GET_ACCOUNT)
    public Account getAccount(@PathVariable Long accountId) {
        return accountService.getAccount(accountId);
    }

}
