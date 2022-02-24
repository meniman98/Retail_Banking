package com.pod2.microservice.customer.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pod2.microservice.customer.model.AccountCreationStatus;
import com.pod2.microservice.customer.model.AccountSummary;
import com.pod2.microservice.customer.model.Customer;
import com.pod2.microservice.customer.model.CustomerCreationStatus;
import com.pod2.microservice.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AccountMicroserviceProxy accountMicroserviceProxy;

	@Value("${message.customer.creation.success}")
	public String MSG_CUSTOMER_CREATION_SUCCESS;
	@Value("${message.account.creation.success}")
	public String MSG_CUSTOMER_ACCOUNT_CREATION_SUCCESS;
	@Value("${message.account.creation.failure}")
	public String MSG_CUSTOMER_ACCOUNT_CREATION_FAILURE;

	@Override
	public CustomerCreationStatus create(Customer customer) {
		// create customer
		Customer newCustomer = customerRepository.save(customer);
		CustomerCreationStatus status = null;
		// if success
		if (null != newCustomer) {
			this.log.info("Successfully saved new Customer in Database with ID = " + newCustomer.getCustomerId());
			// set creation success status
			status = new CustomerCreationStatus(MSG_CUSTOMER_CREATION_SUCCESS);
			newCustomer.getCustomerCreationStatus().add(status);
			// interact with Account service to create Savings & Current Account for
			// the customer
			try {
				this.log.info("Interract with Account Microservice to request Accounts creation for new Customer with ID = " + newCustomer.getCustomerId());
				AccountCreationStatus accountCreationStatus = accountMicroserviceProxy
						.postCreateAccount(newCustomer.getCustomerId());
				if (null != accountCreationStatus && !accountCreationStatus.getMessage().isEmpty()) {
					newCustomer.getCustomerCreationStatus().add(new CustomerCreationStatus(MSG_CUSTOMER_ACCOUNT_CREATION_SUCCESS));
					this.log.info("Successfully created default Accounts for new Customer with ID = " + newCustomer.getCustomerId());
				}
			} catch (Exception e) {
				newCustomer.getCustomerCreationStatus().add(new CustomerCreationStatus(MSG_CUSTOMER_ACCOUNT_CREATION_FAILURE));
				this.log.error("Failed to interract with Account Service and create default accounts for new customer with ID = " + newCustomer.getCustomerId());
			}
			// update new customer status
			customerRepository.save(newCustomer);
		} else {
			this.log.error("Failed to save new customer in Database");
		}
		

		return status;
	}

	@Override
	public Customer getDetailsById(Long customerId) {
		Customer customer = this.customerRepository.findByCustomerId(customerId);
		if (null != customer) {
			this.log.info("Successfully fetched details of Customer with ID = " + customerId + " in Database");
			try {
				this.log.info("Interract with Account Microservice to request Accounts summary of Customer with ID = " + customerId);
				List<AccountSummary> accountsSummary = this.accountMicroserviceProxy
						.getCustomerAccounts(customer.getCustomerId());
				if (null != accountsSummary && !accountsSummary.isEmpty()) {
					customer.setAccountsSummary(accountsSummary);
					this.log.info("Successfully fetched Accounts summary of Customer with ID = " + customerId);
				}
			} catch (Exception e) {
				this.log.error("Failed to interract with Account Service and retrieve accounts summary of customer with ID = " + customerId);
			}
		} else {
			this.log.error("Customer with ID = " + customerId + " not found in the Database");
		}
		return customer;
	}

	@Override
	public Customer getDetailsByFirstName(String firstName) {
		Customer customer = this.customerRepository.findByFirstName(firstName);
		if (null != customer) {
			this.log.info("Successfully fetched details of Customer with First Name = " + firstName + " in Database");
			try {
				this.log.info("Interract with Account Microservice to request Accounts summary of Customer with First Name = " + firstName);
				List<AccountSummary> accountsSummary = this.accountMicroserviceProxy
						.getCustomerAccounts(customer.getCustomerId());
				if (null != accountsSummary && !accountsSummary.isEmpty()) {
					customer.setAccountsSummary(accountsSummary);
					this.log.info("Successfully fetched Accounts summary of Customer with FirstName = " + firstName);
				}
			} catch (Exception e) {
				this.log.error("Failed to interract with Account Service and retrieve accounts summary of customer with First Name = " + firstName);
			}
		} else {
			this.log.error("Customer with First Name = " + firstName + " not found in the Database");
		}
		return customer;
	}

	@Override
	public List<Customer> getAllCustomerDetails() {
		this.log.info("Fetch all customers in DB");
		return this.customerRepository.findAll();
	}

}
