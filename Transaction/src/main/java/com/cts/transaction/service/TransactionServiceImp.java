package com.cts.transaction.service;

import com.cts.transaction.model.Transaction;
import com.cts.transaction.model.TransactionStatus;

import java.util.List;

public class TransactionServiceImp implements TransactionService{

    @Override
    public TransactionStatus deposit(int accountID, double amount) {
        return null;
    }

    @Override
    public TransactionStatus withdraw(int accountID, double amount) {
        return null;
    }

    @Override
    public TransactionStatus transfer(int sourceAccountID, int destAccountID, double amount) {
        return null;
    }

    @Override
    public List<Transaction> getTransaction(int customerID) {
        return null;
    }
}
