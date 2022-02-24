package com.cts.portal.dto;

import lombok.Data;

@Data
public class TransactionStatus {
	private long id;
	private long accountId;
	private String message;
	private double sourceBalance;
	private double destinationBalance;
}
