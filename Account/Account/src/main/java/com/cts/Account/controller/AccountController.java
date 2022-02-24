package com.cts.Account.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.Account.Utils;
import com.cts.Account.model.Account;
import com.cts.Account.model.AccountCreationStatus;
import com.cts.Account.service.AccountServiceImpl;

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
    public List<Account> getCustomerAccounts(@PathVariable Long customerId) {
        return accountService.getCustomerAccounts(customerId);
    }

    //    This method works great
    @GetMapping(Utils.GET_ACCOUNT)
    public Account getAccount(@PathVariable Long accountId) {
        return accountService.getAccount(accountId);
    }


    @GetMapping(Utils.GET_ACCOUNT_BY_NUMBER)
    public Account getAccountNumber(@PathVariable String accountNo) {
        return accountService.getAccountByNo(accountNo);
    }

}
