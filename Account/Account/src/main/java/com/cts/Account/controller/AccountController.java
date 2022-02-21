package com.cts.Account.controller;


import com.cts.Account.Utils;
import com.cts.Account.model.Account;
import com.cts.Account.model.AccountCreationStatus;
import com.cts.Account.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class AccountController {

    @Autowired
    AccountServiceImpl accountService;

    //    this method works very well
    @PostMapping(Utils.CREATE_ACCOUNT)
    public AccountCreationStatus createAccount(@PathVariable Long customerId,
                                               @RequestBody Account account) {
        return accountService.createAccount(customerId, account);
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
