package com.pod2.microservice.customer.service;

import java.util.List;

import com.pod2.microservice.customer.model.Customer;
import com.pod2.microservice.customer.model.CustomerCreationStatus;

public interface CustomerService {
	
	public String MSG_CUSTOMER_CREATION_SUCCESS = "";
	public String MSG_CUSTOMER_ACCOUNT_CREATION_SUCCESS = "";
	public String MSG_CUSTOMER_ACCOUNT_CREATION_FAILURE = "";
	
	public CustomerCreationStatus create(Customer customer, String bearerToken);
	public Customer getDetailsById(Long customerId, String bearerToken);
	public Customer getDetailsByFirstName(String firstName, String bearerToken);
	public List<Customer> getAllCustomerDetails();
}
