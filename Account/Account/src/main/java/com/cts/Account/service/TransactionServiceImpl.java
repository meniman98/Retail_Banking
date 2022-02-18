package com.cts.Account.service;

import com.cts.Account.model.Account;
import com.cts.Account.model.TransactionStatus;
import com.cts.Account.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    AccountRepo accountRepo;

    @Override
    public TransactionStatus deposit(Long accountId, double amount) {
        Account retrievedAccount = accountRepo.getById(accountId);
        double currentBalance = retrievedAccount.getBalance();
        double newBalance = currentBalance + amount;
        retrievedAccount.setBalance(newBalance);
        accountRepo.save(retrievedAccount);

        TransactionStatus status = new TransactionStatus();
        status.setAccountId(retrievedAccount.getAccountId());
        return status;
    }

    @Override
    public TransactionStatus withdraw(Long accountId, double amount) {
        Account retrievedAccount = accountRepo.getById(accountId);
        double currentBalance = retrievedAccount.getBalance();
        double newBalance = currentBalance - amount;
        retrievedAccount.setBalance(newBalance);
        accountRepo.save(retrievedAccount);

        TransactionStatus status = new TransactionStatus();
        status.setAccountId(retrievedAccount.getAccountId());
        return status;
    }
}
