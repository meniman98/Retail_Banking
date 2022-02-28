package com.cts.transaction.controller;

import com.cts.transaction.model.Transaction;
import com.cts.transaction.model.TransactionStatus;
import com.cts.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/deposit/{accountID}/{amount}")
    public TransactionStatus deposit(@PathVariable Long accountID, @PathVariable double amount, @RequestHeader(name = "Authorization", required = false) String bearerToken) {
        return transactionService.deposit(accountID, amount, bearerToken);
    }

    @PostMapping(value = "/withdraw/{accountID}/{amount}")
    public TransactionStatus withdraw(@PathVariable Long accountID, @PathVariable double amount, @RequestHeader(name = "Authorization", required = false) String bearerToken) {
        return transactionService.withdraw(accountID, amount, bearerToken);
    }

    @PostMapping(value = "/transfer/{sourceAccountID}/{destAccountID}/{amount}")
    public TransactionStatus transfer(@PathVariable Long sourceAccountID, @PathVariable Long destAccountID,
                                      @PathVariable double amount, @RequestHeader(name = "Authorization", required = false) String bearerToken) {
        return transactionService.transfer(sourceAccountID, destAccountID, amount, bearerToken);
    }

    @GetMapping(value = "/getTransaction/{customerID}")
    public List<Transaction> getTransaction(@PathVariable Long customerID, @RequestHeader(name = "Authorization", required = false) String bearerToken) {
        return transactionService.getTransaction(customerID, bearerToken);
    }
}
