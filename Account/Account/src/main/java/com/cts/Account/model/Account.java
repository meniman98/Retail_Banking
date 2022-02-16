package com.cts.Account.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;
    private String accountNo;
    private LocalDate dateOfCreation;
    private String accountType;
    private double balance;
    private Customer customerId;

}
