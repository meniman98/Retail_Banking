package com.cts.transaction.service;

import com.cts.transaction.model.Transaction;
import com.cts.transaction.model.TransactionStatus;

import java.util.List;

public interface TransactionService {
    public TransactionStatus deposit (int accountID, double amount);
    public TransactionStatus withdraw (int accountID, double amount);
    public TransactionStatus transfer (int sourceAccountID, int destAccountID, double amount);
    public List<Transaction> getTransaction(int customerID);
}
