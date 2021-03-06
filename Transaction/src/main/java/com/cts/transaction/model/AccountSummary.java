package com.cts.transaction.model;
import lombok.Data;

@Data
public class AccountSummary {
	private String accountNo;
	private String accountType;
	private String dateOfCreation;
	private Long accountId;
	private double balance;
	private long customerId;

	public AccountSummary(String accountNo, String accountType, String dateOfCreation, Long accountId, double balance,
						  long customerId) {
		super();
		this.accountNo = accountNo;
		this.accountType = accountType;
		this.dateOfCreation = dateOfCreation;
		this.accountId = accountId;
		this.balance = balance;
		this.customerId = customerId;
	}
	
	
}