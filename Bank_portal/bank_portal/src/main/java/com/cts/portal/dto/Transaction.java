package com.cts.portal.dto;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
public class Transaction {
	private int transactionID;
	private Long accountID;
	private int statementID;
	private Counterparty counterParty;
	private LocalDate transactionDate;
	private String transactionType;
	private String transactionStatus;
	private String amount;
}
