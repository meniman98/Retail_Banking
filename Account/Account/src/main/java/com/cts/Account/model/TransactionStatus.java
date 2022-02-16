package com.cts.Account.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class TransactionStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionStatusId;
    private Account accountId;
    private String message;
    private double sourceBalance;
    private double destinationBalance;


}
