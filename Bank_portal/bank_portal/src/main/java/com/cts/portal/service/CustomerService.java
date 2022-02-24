package com.cts.portal.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.portal.dto.Customer;
import com.cts.portal.dto.CustomerCreationStatus;
import com.cts.portal.model.Authority;
import com.cts.portal.model.BankUser;
import com.cts.portal.repository.AuthorityRepository;
import com.cts.portal.repository.BankUserRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerMicroserviceProxy customerProxy;
	
	@Autowired
	BankUserRepository userRepo;
	
	@Autowired
	AuthorityRepository authorityRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<Customer> getCustomers() {
		try {
			return this.customerProxy.getAllCustomerDetails();
		} catch(Exception e) {
			return null;
		}
	}
	
	public BankUser getUser(String email) {
		return this.userRepo.findByEmail(email);
	}
	
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
			CustomerCreationStatus status = this.customerProxy.postCreateAccount(newCustomer);
			BankUser newCustomerUser = new BankUser();
			newCustomerUser.setAuthorities(new HashSet<>());
			newCustomerUser.setPassword(this.passwordEncoder.encode(customer.getPassword()));
			newCustomerUser.setEmail(customer.getEmail());
			newCustomerUser.setCustomerFirstName(customer.getFirstName());
			Authority customerAuthority = new Authority();
			customerAuthority.setName("ROLE_CUSTOMER");
			newCustomerUser.getAuthorities().add(customerAuthority);
			this.userRepo.save(newCustomerUser);
			return status;
		} catch(Exception e) {
			e.printStackTrace();
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
