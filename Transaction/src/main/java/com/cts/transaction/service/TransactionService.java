package com.cts.transaction.service;

import com.cts.transaction.model.Transaction;
import com.cts.transaction.model.TransactionStatus;

import java.util.List;

public interface TransactionService {
    public TransactionStatus deposit (Long accountID, double amount);
    public TransactionStatus withdraw (Long accountID, double amount);
    public TransactionStatus transfer (Long sourceAccountID, Long destAccountID, double amount);
    public List<Transaction> getTransaction(Long customerID);
}
