package com.cts.transaction.service;

import com.cts.transaction.model.Transaction;
import com.cts.transaction.model.TransactionStatus;

import java.util.List;

public interface TransactionService {
    TransactionStatus deposit (Long accountID, double amount, String bearerToken);
    TransactionStatus withdraw(Long accountID, double amount, String bearerToken);
    TransactionStatus transfer(Long sourceAccountID, Long destAccountID, double amount, String bearerToken);
    List<Transaction> getTransaction(Long customerID, String bearerToken);
}
