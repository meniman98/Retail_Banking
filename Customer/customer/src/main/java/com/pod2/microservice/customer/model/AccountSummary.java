package com.pod2.microservice.customer.model;

import lombok.Data;

@Data
public class AccountSummary {
	private String accountNo;
	private String accountType;
	private String dateOfCreation;
	private Long accountId;
	private double balance;
	
	public AccountSummary(String accountNo, String accountType, String dateOfCreation, Long accountId, double balance) {
		super();
		this.accountNo = accountNo;
		this.accountType = accountType;
		this.dateOfCreation = dateOfCreation;
		this.accountId = accountId;
		this.balance = balance;
	}
	
	
}
