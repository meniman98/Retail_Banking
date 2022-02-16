package com.pod2.microservice.customer.service;

import com.pod2.microservice.customer.model.Customer;
import com.pod2.microservice.customer.model.CustomerCreationStatus;

public interface CustomerService {
	public CustomerCreationStatus create(Customer customer);
	public Customer getDetailsById(Long customerId);
}
