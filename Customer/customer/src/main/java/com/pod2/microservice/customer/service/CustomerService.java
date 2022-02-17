package com.pod2.microservice.customer.service;

import org.springframework.beans.factory.annotation.Value;

import com.pod2.microservice.customer.model.Customer;
import com.pod2.microservice.customer.model.CustomerCreationStatus;

public interface CustomerService {
	
	public String MSG_CUSTOMER_CREATION_SUCCESS = "";
	public String MSG_CUSTOMER_ACCOUNT_CREATION_SUCCESS = "";
	public String MSG_CUSTOMER_ACCOUNT_CREATION_FAILURE = "";
	
	public CustomerCreationStatus create(Customer customer);
	public Customer getDetailsById(Long customerId);
}
