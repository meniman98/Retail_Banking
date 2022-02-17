package com.pod2.microservice.customer.model;

import lombok.Data;

@Data
public class AccountCreationStatus {
	private String message;
	private Long accountId;

	public AccountCreationStatus(String message, Long accountId) {
		super();
		this.message = message;
		this.accountId = accountId;
	}

}
