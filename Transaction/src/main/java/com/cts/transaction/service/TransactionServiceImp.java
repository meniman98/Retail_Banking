package com.cts.transaction.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.transaction.model.AccountSummary;
import com.cts.transaction.model.Counterparty;
import com.cts.transaction.model.CustomerSummary;
import com.cts.transaction.model.RuleStatus;
import com.cts.transaction.model.Transaction;
import com.cts.transaction.model.TransactionStatus;
import com.cts.transaction.repo.TransactionRepo;

@Service
public class TransactionServiceImp implements TransactionService {
  
    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private AccountMicroserviceProxy accountMicroserviceProxy;

    @Autowired
    private CustomerMicroserviceProxy customerMicroserviceProxy;

    @Autowired
    private RuleMicroserviceProxy ruleMicroserviceProxy;

    @Override
    public TransactionStatus deposit(Long accountId, double amount, String bearerToken) {
        try {
            AccountSummary accountData = accountMicroserviceProxy.getAccount(accountId, bearerToken);
            CustomerSummary customerData = customerMicroserviceProxy.getCustomerDetails(accountData.getCustomerId(), bearerToken);
            TransactionStatus transactionStatus = accountMicroserviceProxy.deposit(accountId, amount, bearerToken);
            transactionStatus.setMessage("completed");
            transactionHistory(accountId, "+" + amount, customerData, "deposit",
                    "completed");
            return transactionStatus;
        } catch (Exception e) {
        	e.printStackTrace();
            // cancel transaction if failed to communicate with Account Microservice
            TransactionStatus canceledTransaction = new TransactionStatus();
            canceledTransaction.setAccountId(accountId);
            canceledTransaction.setMessage("canceled");
            return canceledTransaction;
        }
    }

    @Override
    public TransactionStatus withdraw(Long accountId, double amount, String bearerToken) {
        try {
            // get account info
            AccountSummary accountData = accountMicroserviceProxy.getAccount(accountId, bearerToken);
            CustomerSummary customerData = customerMicroserviceProxy.getCustomerDetails(accountData.getCustomerId(), bearerToken);
            // check wether the withdrawal will result in non maintenance of min balance
            double balanceAfterWithdrawal = accountData.getBalance() - amount;
            RuleStatus ruleStatus = ruleMicroserviceProxy.evaluateMinBal(balanceAfterWithdrawal,
                    accountData.getAccountType());
            // decline the transaction if it doesn't respect min balance
            if (ruleStatus.getStatus().equals("denied")) {
                TransactionStatus declinedStatus = new TransactionStatus();
                declinedStatus.setAccountId(accountId);
                declinedStatus.setMessage("declined");
                transactionHistory(accountId, "-" + amount, customerData, "withdraw",
                        "declined");
                return declinedStatus;
            }
            // otherwise, proceed to withdrawal
            TransactionStatus transactionStatus = accountMicroserviceProxy.withdraw(accountId, amount, bearerToken);
            transactionStatus.setMessage("completed");
            transactionHistory(accountId, "-" + amount, customerData, "withdraw",
                    "completed");
            return transactionStatus;
        } catch (Exception e) {
        	e.printStackTrace();
            // cancel transaction if failed to communicate with Account or Rules Microservices
            TransactionStatus canceledTransaction = new TransactionStatus();
            canceledTransaction.setAccountId(accountId);
            canceledTransaction.setMessage("canceled");
            return canceledTransaction;
        }
    }

    @Override
    public TransactionStatus transfer(Long sourceAccountID, Long destAccountID, double amount, String bearerToken) {

        try {
            // get account info
            AccountSummary accountData = accountMicroserviceProxy.getAccount(sourceAccountID, bearerToken);
            AccountSummary accountDataDest = accountMicroserviceProxy.getAccount(destAccountID, bearerToken);
            CustomerSummary customerData = customerMicroserviceProxy.getCustomerDetails(accountDataDest.getCustomerId(), bearerToken);
            // check wether the withdrawal will result in non maintenance of min balance
            double balanceAfterTransfer = accountData.getBalance() - amount;
            RuleStatus ruleStatus = ruleMicroserviceProxy.evaluateMinBal(balanceAfterTransfer,
                    accountData.getAccountType());
            // decline the transaction if it doesn't respect min balance
            if (ruleStatus.getStatus().equals("denied")) {
                TransactionStatus declinedStatus = new TransactionStatus();
                declinedStatus.setAccountId(sourceAccountID);
                declinedStatus.setMessage("declined");
                transactionHistory(sourceAccountID, "-" + amount, customerData, "transfer",
                        "declined");
                return declinedStatus;
            }
            // otherwise, proceed to withdrawal
            TransactionStatus transactionStatus = accountMicroserviceProxy.withdraw(sourceAccountID, amount, bearerToken);
            transactionStatus.setMessage("completed");
            transactionHistory(sourceAccountID, "-" + amount, customerData, "transfer",
                    "completed");
            accountMicroserviceProxy.deposit(destAccountID, amount, bearerToken);
            transactionHistory(destAccountID, "+" + amount, customerData, "transfer",
                    "completed");
            return transactionStatus;
        } catch (Exception e) {
            // cancel transaction if failed to communicate with Account or Rules Microservices
            TransactionStatus canceledTransaction = new TransactionStatus();
            canceledTransaction.setAccountId(sourceAccountID);
            canceledTransaction.setMessage("canceled");
            return canceledTransaction;
        }

    }

    @Override
    public List<Transaction> getTransaction(Long customerID, String bearerToken) {
        List<AccountSummary> accounts = accountMicroserviceProxy.getCustomerAccounts(customerID, bearerToken);
        List<Transaction> transactionList = new ArrayList<>();
        for (AccountSummary accountData : accounts) {
            transactionList.addAll(transactionRepo.findByAccountID(accountData.getAccountId()));
        }
        Collections.reverse(transactionList);
        return transactionList;
    }

    private void transactionHistory(Long accountId, String amount, CustomerSummary customerData,
                                    String transactionType, String transactionStatus) {

        Transaction transaction = new Transaction();
        transaction.setAccountID(accountId);
        Counterparty counterparty = new Counterparty();
        counterparty.setName(customerData.getFirstName() + " " + customerData.getLastName());
        transaction.setCounterParty(counterparty);
        transaction.setTransactionDate(LocalDate.now());
        transaction.setTransactionType(transactionType);
        transaction.setTransactionStatus(transactionStatus);
        transaction.setAmount(amount);
        transactionRepo.save(transaction);

    }
}
