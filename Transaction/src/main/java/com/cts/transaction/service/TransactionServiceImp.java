package com.cts.transaction.service;

import com.cts.transaction.model.RuleStatus;
import com.cts.transaction.model.Transaction;
import com.cts.transaction.model.TransactionStatus;
import com.cts.transaction.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransactionServiceImp implements TransactionService{

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private AccountMicroserviceProxy accountMicroserviceProxy;

    @Autowired
    private RuleMicroserviceProxy ruleMicroserviceProxy;

    @Override
    public TransactionStatus deposit(Long accountID, double amount) {
        TransactionStatus transactionStatus = accountMicroserviceProxy.deposit(accountID, amount);
        transactionStatus.setMessage("completed");
        return transactionStatus;
    }

    @Override
    public TransactionStatus withdraw(Long accountID, double amount) {
        TransactionStatus transactionStatus = accountMicroserviceProxy.withdraw(accountID, amount);
        //RuleStatus ruleStatus = ruleMicroserviceProxy.evaluateMinBal();
        return transactionStatus;
    }

    @Override
    public TransactionStatus transfer(Long sourceAccountID, Long destAccountID, double amount) {
        return null;
    }

    @Override
    public List<Transaction> getTransaction(Long customerID) {
        return null;
    }
}
