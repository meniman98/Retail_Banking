package com.pod2.microservice.customer.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String address;
	private String dateOfBirth;
	
	@OneToMany(fetch=FetchType.EAGER)
	private List<CustomerCreationStatus> customerCreationStatus;
}
