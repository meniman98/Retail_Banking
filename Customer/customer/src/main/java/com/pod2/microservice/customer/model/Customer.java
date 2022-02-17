package com.pod2.microservice.customer.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String address;
	private String dateOfBirth;

	@Transient
	private List<AccountSummary> accountsSummary = new ArrayList<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private List<CustomerCreationStatus> customerCreationStatus = new ArrayList<>();

	public Customer(Long customerId, String firstName, String lastName, String email, String mobileNumber,
			String address, String dateOfBirth) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.customerCreationStatus = new ArrayList<>();
	}

	public Customer() {
		super();
	}

}
