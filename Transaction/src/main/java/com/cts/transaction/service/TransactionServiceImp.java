package com.cts.transaction.service;

import com.cts.transaction.model.AccountSummary;
import com.cts.transaction.model.RuleStatus;
import com.cts.transaction.model.Transaction;
import com.cts.transaction.model.TransactionStatus;
import com.cts.transaction.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImp implements TransactionService {

	@Autowired
	private TransactionRepo transactionRepo;

	@Autowired
	private AccountMicroserviceProxy accountMicroserviceProxy;

	@Autowired
	private RuleMicroserviceProxy ruleMicroserviceProxy;

	@Override
	public TransactionStatus deposit(Long accountId, double amount) {
		try {
			TransactionStatus transactionStatus = accountMicroserviceProxy.deposit(accountId, amount);
			transactionStatus.setMessage("completed");
			return transactionStatus;
		} catch (Exception e) {
			// cancel transaction if failed to communicate with Account Microservice
			TransactionStatus canceledTransaction = new TransactionStatus();
			canceledTransaction.setAccountId(accountId);
			canceledTransaction.setMessage("canceled");
			return canceledTransaction;
		}
	}

	@Override
	public TransactionStatus withdraw(Long accountId, double amount) {
		try {
			// get account info
			AccountSummary accountData = accountMicroserviceProxy.getAccount(accountId);
			// check wether the withdrawal will result in non maintenance of min balance
			double balanceAfterWithdrawal = accountData.getBalance() - amount;
			RuleStatus ruleStatus = ruleMicroserviceProxy.evaluateMinBal(balanceAfterWithdrawal,
					accountData.getAccountType());
			// decline the transaction if it doesn't respect min balance
			if (ruleStatus.getStatus().equals("denied")) {
				TransactionStatus declinedStatus = new TransactionStatus();
				declinedStatus.setAccountId(accountId);
				declinedStatus.setMessage("declined");
				return declinedStatus;
			}
			// otherwise, proceed to withdrawal
			TransactionStatus transactionStatus = accountMicroserviceProxy.withdraw(accountId, amount);
			transactionStatus.setMessage("completed");
			return transactionStatus;
		} catch (Exception e) {
			// cancel transaction if failed to communicate with Account or Rules Microservices
			TransactionStatus canceledTransaction = new TransactionStatus();
			canceledTransaction.setAccountId(accountId);
			canceledTransaction.setMessage("canceled");
			return canceledTransaction;
		}
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
