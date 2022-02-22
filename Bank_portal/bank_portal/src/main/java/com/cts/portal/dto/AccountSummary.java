package com.cts.portal.dto;
import lombok.Data;

@Data
public class AccountSummary {
	private String accountNo;
	private String accountType;
	private String dateOfCreation;
	private Long accountId;
	private double balance;
}