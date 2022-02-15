package com.cts.Account.model;

import lombok.Data;

import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short accountId;
    private String accountNo;
    private LocalDate dateOfCreation;
    private String accountType;
    private double balance;
    private Customer customerId;

}
