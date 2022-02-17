package com.cts.Account.controller;


import com.cts.Account.Utils;
import com.cts.Account.model.Account;
import com.cts.Account.model.AccountCreationStatus;
import com.cts.Account.model.Customer;
import com.cts.Account.repo.AccountRepo;
import com.cts.Account.repo.CustomerRepo;
import com.cts.Account.repo.StatementRepo;
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
    AccountRepo accountRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    StatementRepo statementRepo;

//    this method works very well
    @PostMapping(Utils.CREATE_ACCOUNT)
    public AccountCreationStatus createAccount(@PathVariable Long customerId) {
        if (customerRepo.existsById(customerId)) {
//            Make a new account, and pass in the customer ID
//            as a parameter
            Customer retrievedCustomer = customerRepo.getById(customerId);
            Account newAccount = new Account(retrievedCustomer.getCustomerId());
            retrievedCustomer.getAccountSet().add(newAccount);
            accountRepo.save(newAccount);
            return new AccountCreationStatus
                    (newAccount, "Account successfully created");
        }
//        if customerId does not exist
        else if (!customerRepo.existsById(customerId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer ID not found");
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//    this method works great
    @GetMapping(Utils.GET_CUSTOMER_ACCOUNTS)
    public Set<Account> getCustomerAccounts(@PathVariable Long customerId) {
        if (customerRepo.existsById(customerId)) {
            Customer customer = customerRepo.getById(customerId);
            return customer.getAccountSet();
        } else if (!customerRepo.existsById(customerId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Utils.CUSTOMER_NOT_FOUND);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    This method works great
    @GetMapping(Utils.GET_ACCOUNT)
    public Account getAccount(@PathVariable Long accountId) {
        if (accountRepo.existsById(accountId)) {
            return accountRepo.getById(accountId);
        }
        else if (!accountRepo.existsById(accountId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Utils.ACCOUNT_NOT_FOUND);
        }
        else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
