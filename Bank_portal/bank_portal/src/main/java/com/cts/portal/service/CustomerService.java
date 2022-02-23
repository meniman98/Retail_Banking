package com.cts.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.portal.dto.Customer;
import com.cts.portal.dto.CustomerCreationStatus;

@Service
public class CustomerService {
	
	@Autowired
	CustomerMicroserviceProxy customerProxy;
	
	public Customer getCustomer(String firstName) {
		try {
			return this.customerProxy.getCustomerDetailsByFirstName(firstName);
		} catch(Exception e) {
			return null;
		}
	}
	
	public CustomerCreationStatus createCustomer(Customer customer) {
		Customer newCustomer = this.buildCustomer(customer);
		try {
			return this.customerProxy.postCreateAccount(newCustomer);
		} catch(Exception e) {
			CustomerCreationStatus failedStatus = new CustomerCreationStatus();
			failedStatus.setMessage("ko");
			return failedStatus;
		}
	}
	
	private Customer buildCustomer(Customer customer) {
		Customer newCustomer = new Customer();
		newCustomer.setFirstName(customer.getFirstName());
		newCustomer.setLastName(customer.getLastName());
		newCustomer.setEmail(customer.getEmail());
		newCustomer.setMobileNumber(customer.getMobileNumber());
		newCustomer.setAddress(customer.getAddress1() + "," + customer.getAddress2() + "," + customer.getCountry() + "," + customer.getState() + "," + customer.getZip());
		newCustomer.setDateOfBirth(customer.getDateOfBirth());
		return newCustomer;
	}
}
