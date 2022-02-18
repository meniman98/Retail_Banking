package com.cts.Account.controller;


import com.cts.Account.Utils;
import com.cts.Account.model.TransactionStatus;
import com.cts.Account.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionService;

    @PostMapping(Utils.DEPOSIT)
    public TransactionStatus deposit(@PathVariable Long accountId, @PathVariable double amount) {
        return transactionService.deposit(accountId, amount);
    }

    @PostMapping(Utils.WITHDRAW)
    public TransactionStatus withdraw(@PathVariable Long accountId, @PathVariable double amount) {
        return transactionService.withdraw(accountId, amount);
    }
}
