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
}
