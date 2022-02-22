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
    public AccountCreationStatus createAccount(Long customerId, Account baseAccount) {
        if (customerRepo.existsById(customerId)) {
//            Make a new account, and pass in the customer ID
//            as a parameter
            Customer retrievedCustomer = customerRepo.getById(customerId);

            baseAccount.setCustomerId(retrievedCustomer.getCustomerId());

            Account savingsAccount = createAccount(baseAccount, "Savings");

            Account currentAccount = createAccount(baseAccount, "Current");

//            Add a savings account
            /*FIXME: the creation of 2 accounts works fine in the DB, but only the
            *  "current" account gets added to the accountSet of the Customer*/
            retrievedCustomer.getAccountSet().add(savingsAccount);
            accountRepo.save(savingsAccount);

//            Add a current account
            retrievedCustomer.getAccountSet().add(currentAccount);
            accountRepo.save(currentAccount);


            return new AccountCreationStatus
                    (baseAccount, "Account successfully created");
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

    private Account createAccount(Account baseAccount, String accountType) {
        return new Account(
                accountType,
                baseAccount.getAccountNo(),
                baseAccount.getBalance(),
                baseAccount.getCustomerId(),
                LocalDate.now()
        );
    }

}
