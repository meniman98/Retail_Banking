package com.pod2.microservice.customer.service;

import java.util.Arrays;
import java.util.List;

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
			// set creation success status
			status = new CustomerCreationStatus(MSG_CUSTOMER_CREATION_SUCCESS);
			newCustomer.getCustomerCreationStatus().add(status);
			// interact with Account service to create Savings & Current Account for
			// the customer
			CustomerCreationStatus accountStatus = new CustomerCreationStatus(MSG_CUSTOMER_ACCOUNT_CREATION_FAILURE);
			try {
				AccountCreationStatus accountCreationStatus = accountMicroserviceProxy
						.postCreateAccount(newCustomer.getCustomerId());
				if (null != accountCreationStatus && !accountCreationStatus.getMessage().isEmpty()) {
					accountStatus = new CustomerCreationStatus(MSG_CUSTOMER_ACCOUNT_CREATION_FAILURE);
				}
			} catch (Exception e) {
				// TODO : logg error
			}
			newCustomer.getCustomerCreationStatus().add(accountStatus);
			// update new customer status
			customerRepository.save(newCustomer);
			return status;
		}

		return status;
	}

	@Override
	public Customer getDetailsById(Long customerId) {
		Customer customer = this.customerRepository.findByCustomerId(customerId);
		if (null != customer) {
			try {
				List<AccountSummary> accountsSummary = this.accountMicroserviceProxy
						.getCustomerAccounts(customer.getCustomerId());
				if (null != accountsSummary && !accountsSummary.isEmpty()) {
					customer.setAccountsSummary(accountsSummary);
				}
			} catch (Exception e) {
				// TODO : logg error
			}
		}
		return customer;
	}

}
