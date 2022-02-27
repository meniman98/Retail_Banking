package com.cts.portal.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.portal.dto.AccountSummary;
import com.cts.portal.dto.Operation;
import com.cts.portal.dto.Statement;
import com.cts.portal.dto.StatementQuery;
import com.cts.portal.dto.Transaction;
import com.cts.portal.dto.TransactionStatus;
import com.cts.portal.dto.Transfer;

@Service
public class TransactionService {
	@Autowired
	TransactionMicroserviceProxy transactionProxy;
	
	@Autowired
	AccountMicroserviceProxy accountProxy;
	
	public TransactionStatus postTransfer(@Valid Transfer transfer, String bearerToken) {
		try {
			AccountSummary destAccount = this.accountProxy.getAccountNumber(transfer.getTargetAccountNo());
			return transactionProxy.transfer(transfer.getSourceAccountId(), destAccount.getAccountId(), transfer.getAmount(), bearerToken);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public TransactionStatus postOperation(Operation operation, String bearerToken) {
		try {
			if (operation.getName().equals("Deposit")) {
				return this.transactionProxy.deposit(operation.getAccountId(), operation.getAmount(), bearerToken);
			} else {
				return this.transactionProxy.withdraw(operation.getAccountId(), operation.getAmount(), bearerToken);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Transaction> getTransactions(Long customerId) {
		try {
			return this.transactionProxy.getTransaction(customerId);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Statement> getStatements(Long accountId, LocalDate startDate, LocalDate endDate) {
		try {
			return this.accountProxy.getStatementList(accountId, startDate, endDate);
		} catch(Exception e) {
			return null;
		}
	}

}
