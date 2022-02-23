package com.cts.transaction.model;

import lombok.Data;

@Data
public class CustomerSummary {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String address;
    private String dateOfBirth;

    public CustomerSummary(Long customerId, String firstName, String lastName, String email, String mobileNumber,
                           String address, String dateOfBirth) {
        super();
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }
}
