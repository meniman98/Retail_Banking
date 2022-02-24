package com.cts.portal.dto;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Customer {
	private Long customerId;
	@Size(min=3, message="Enter at least 3 characters...")
	private String firstName;
	@Size(min=3, message="Enter at least 3 characters...")
	private String lastName;
	@Email(message="Email should be valid...")
	private String email;
	@Pattern(regexp="[0-9]{10}", message="Enter 10 digits...")
	private String mobileNumber;
	private String address;
	@Pattern(regexp="[0-9]{2}/[0-9]{2}/[0-9]{4}", message="Enter a valid date...")
	private String dateOfBirth;
	@NotNull
	@Size(min=5, message="Enter a valid address...")
	private String address1;
	private String address2;
	@NotNull 
	@Size(min=2, message="Enter a valid country name...")
	private String country;
	@NotNull 
	@Size(min=2, message="Enter a valid state name...")
	private String state;
	@NotNull 
	@Digits(fraction = 0, integer = 10000)
	private int zip;
	@NotNull
	@Size(min=2, message="Enter at least 2 characters....")
	private String password;
	
	private List<AccountSummary> accountsSummary;
}
