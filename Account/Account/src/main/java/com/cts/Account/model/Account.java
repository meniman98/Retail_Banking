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
    private Customer customer;

    /*Creating a constructor for customerId only
    * in order to ensure that a value is passed in*/
    public Account(Customer customer) {
        this.customer = customer;
    }
}
