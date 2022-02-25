package com.cts.Account.service;

import com.cts.Account.model.Account;
import com.cts.Account.model.Statement;
import com.cts.Account.model.TransactionStatus;
import com.cts.Account.repo.AccountRepo;

import java.time.LocalDate;

import org.apache.commons.lang.RandomStringUtils;
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
        
     // attach as statement
        Statement statement = new Statement();
        statement.setAccountId(accountId);
        statement.setDate(LocalDate.now());
        statement.setDeposit(amount);
        statement.setNarration("Online Deposit from bank portal app");
        statement.setRefNo(RandomStringUtils.random(4, false, true));
        statement.setValueDate(LocalDate.now());
        statement.setBalance(newBalance);
        retrievedAccount.getStatementSet().add(statement);
        
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
        
        // attach as statement
        Statement statement = new Statement();
        statement.setAccountId(accountId);
        statement.setDate(LocalDate.now());
        statement.setWithdrawal(amount);
        statement.setNarration("Online Withdrawal from bank portal app");
        statement.setRefNo(RandomStringUtils.random(4, false, true));
        statement.setValueDate(LocalDate.now());
        statement.setBalance(newBalance);
        retrievedAccount.getStatementSet().add(statement);
        
        accountRepo.save(retrievedAccount);

        TransactionStatus status = new TransactionStatus();
        status.setAccountId(retrievedAccount.getAccountId());
        return status;
    }
}
