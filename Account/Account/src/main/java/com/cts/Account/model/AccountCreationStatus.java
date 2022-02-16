package com.cts.Account.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class AccountCreationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long AccountCreationStatusId;
    private Account AccountId;
    private String message;


}
