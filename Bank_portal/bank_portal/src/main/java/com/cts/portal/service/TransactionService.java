package com.cts.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.portal.dto.Operation;
import com.cts.portal.dto.Transaction;
import com.cts.portal.dto.TransactionStatus;

@Service
public class TransactionService {
	@Autowired
	TransactionMicroserviceProxy transactionProxy;

	public TransactionStatus postOperation(Operation operation) {
		try {
			if (operation.getName().equals("Deposit")) {
				return this.transactionProxy.deposit(operation.getAccountId(), operation.getAmount());
			} else {
				return this.transactionProxy.withdraw(operation.getAccountId(), operation.getAmount());
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
}
