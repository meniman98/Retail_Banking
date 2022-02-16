package com.pod2.microservice.customer.model;

import lombok.Data;

@Data
public class AccountSummary {
	private String accountNo;
	private String accountType;
	private String dateOfCreation;
	
	public AccountSummary(String accountNo, String accountType, String dateOfCreation) {
		super();
		this.accountNo = accountNo;
		this.accountType = accountType;
		this.dateOfCreation = dateOfCreation;
	}
	
	
}
