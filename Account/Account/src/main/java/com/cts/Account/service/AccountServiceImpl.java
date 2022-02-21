package com.cts.Account.service;

import com.cts.Account.Utils;
import com.cts.Account.model.Account;
import com.cts.Account.model.AccountCreationStatus;
import com.cts.Account.model.Customer;
import com.cts.Account.repo.AccountRepo;
import com.cts.Account.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    AccountRepo accountRepo;

    @Override
    public AccountCreationStatus createAccount(Long customerId, Account account) {
        if (customerRepo.existsById(customerId)) {
//            Make a new account, and pass in the customer ID
//            as a parameter
            Customer retrievedCustomer = customerRepo.getById(customerId);

            account.setCustomerId(retrievedCustomer.getCustomerId());
            account.setDateOfCreation(LocalDate.now());

//            Add a savings account
            account.setAccountType("Savings");
            retrievedCustomer.getAccountSet().add(account);
            accountRepo.save(account);

//            Add a current account
//            FIXME add two accounts at the same time
            account.setAccountType("Current");
            retrievedCustomer.getAccountSet().add(account);
            accountRepo.save(account);


            return new AccountCreationStatus
                    (account, "Account successfully created");
        }
//        if customerId does not exist
        else if (!customerRepo.existsById(customerId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer ID not found");
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Set<Account> getCustomerAccounts(Long customerId) {
        if (customerRepo.existsById(customerId)) {
            Customer customer = customerRepo.getById(customerId);
            return customer.getAccountSet();
        } else if (!customerRepo.existsById(customerId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Utils.CUSTOMER_NOT_FOUND);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Account getAccount(Long accountId) {
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
