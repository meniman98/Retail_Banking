package com.cts.Account.controller;


import com.cts.Account.Utils;
import com.cts.Account.model.Account;
import com.cts.Account.model.AccountCreationStatus;
import com.cts.Account.repo.AccountRepo;
import com.cts.Account.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {




    @Autowired
    AccountRepo accountRepo;

    @Autowired
    CustomerRepo customerRepo;

    @PostMapping(Utils.CREATE_ACCOUNT)
    public AccountCreationStatus createAccount(@PathVariable Long id) throws Exception {
        if (customerRepo.existsById(id)) {
//            Make a new account, and pass in the customer ID
//            as a parameter
            Account newAccount = new Account(customerRepo.getById(id));
            accountRepo.save(newAccount);
            return new AccountCreationStatus
                    (newAccount, "Account successfully created");
        }
//        if customerId does not exist
        else if (!customerRepo.existsById(id)) {
            return new AccountCreationStatus
                    (null, "Customer ID does not exist");
        }
        else {
//            TODO: make dedicated Exception classes
            throw new Exception();
        }

    }
}
