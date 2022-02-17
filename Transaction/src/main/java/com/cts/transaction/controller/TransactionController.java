package com.cts.transaction.controller;

import com.cts.transaction.model.Transaction;
import com.cts.transaction.model.TransactionStatus;
import com.cts.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/deposit/{accountID}")
    public TransactionStatus deposit(@PathVariable int accountID, double amount) {
        return transactionService.deposit(accountID, amount);
    }

    @PostMapping(value = "/withdraw/{accountID}")
    public TransactionStatus withdraw(@PathVariable int accountID, double amount) {
        return transactionService.withdraw(accountID, amount);
    }

    @PostMapping(value = "/transfer/{accountID}")
    public TransactionStatus transfer(@PathVariable int sourceAccountID, int destAccountID, double amount) {
        return transactionService.transfer(sourceAccountID, destAccountID, amount);
    }

    @GetMapping(value = "/getTransaction/{customerID}")
    public List<Transaction> getTransaction(@PathVariable int customerID) {
        return transactionService.getTransaction(customerID);
    }
}
