package com.cts.transaction.service;

import com.cts.transaction.model.Transaction;
import com.cts.transaction.model.TransactionStatus;

import java.util.List;

public interface TransactionService {
    TransactionStatus deposit (Long accountID, double amount);
    TransactionStatus withdraw(Long accountID, double amount);
    TransactionStatus transfer(Long sourceAccountID, Long destAccountID, double amount);
    List<Transaction> getTransaction(Long customerID);
}
