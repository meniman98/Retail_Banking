package com.pod2.microservice.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.pod2.microservice.customer.model.AccountCreationStatus;
import com.pod2.microservice.customer.model.AccountSummary;
import com.pod2.microservice.customer.model.Customer;
import com.pod2.microservice.customer.model.CustomerCreationStatus;
import com.pod2.microservice.customer.repository.CustomerRepository;

public class CustomerServiceTest {
	
	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private AccountMicroserviceProxy accountMicroserviceProxy;
	
	private Customer validCustomer = new Customer(1L, "Test", "Test", "test@test.com", "0123456789", "1, Test France", "01/01/1998");
	private Customer invalidCustomer = new Customer(0L, "", "", "", "", "1, Test France", "01/01/1998");
	
	private AccountCreationStatus accountStatus = new AccountCreationStatus("Accounts successfully created !", 1L);
	private List<AccountSummary> accountsSummary = new ArrayList<>();
	
	private final Long SAVED_CUSTOMER_ID = 1L;
	private final Long UNSAVED_CUSTOMER_ID = 5L;
	
	
	@InjectMocks
	private CustomerServiceImpl customerService;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		this.accountsSummary.add(new AccountSummary("12345", "Savings", "01/01/2022"));
		this.accountsSummary.add(new AccountSummary("54321", "Current", "01/01/2022"));
		
		// Mock CustomerRepository
		Mockito.when(this.customerRepository.save(this.validCustomer)).thenReturn(this.validCustomer);
		Mockito.when(this.customerRepository.save(invalidCustomer)).thenReturn(null);
		Mockito.when(this.customerRepository.findByCustomerId(SAVED_CUSTOMER_ID)).thenReturn(this.validCustomer);
		Mockito.when(this.customerRepository.findByCustomerId(UNSAVED_CUSTOMER_ID)).thenReturn(null);		
		
		// Mock AccountMicroserviceProxy
		Mockito.when(this.accountMicroserviceProxy.postCreateAccount(this.validCustomer.getCustomerId())).thenReturn(this.accountStatus);
		Mockito.when(this.accountMicroserviceProxy.getCustomerAccounts(this.validCustomer.getCustomerId())).thenReturn(this.accountsSummary);
	}
	
	@Test 
	public void testCreateValidCustomerReturnStatusObject() {
		CustomerCreationStatus status = this.customerService.create(this.validCustomer);
		assertNotNull(status);
	}
	
	@Test
	public void testCreateInvalidCustomerReturnNull() {
		CustomerCreationStatus status = this.customerService.create(this.invalidCustomer);
		assertNull(status);
	}
	
	@Test
	public void testCreateValidCustomerCallAccountMicroservicePostCreateAccountOnce() {
		this.customerService.create(this.validCustomer);
		verify(this.accountMicroserviceProxy, times(1)).postCreateAccount(this.validCustomer.getCustomerId());
	}
	
	@Test
	public void testCreateInvalidCustomerNeverCallAccountMicroservice() {
		this.customerService.create(this.invalidCustomer);
		verify(this.accountMicroserviceProxy, never()).postCreateAccount(this.invalidCustomer.getCustomerId());
	}
	
	@Test
	public void testCreateValidCustomerAddCreationStatus() {
		CustomerCreationStatus status = this.customerService.create(this.validCustomer);
		assertEquals(CustomerServiceImpl.MSG_CUSTOMER_CREATION_SUCCESS, status.getMessage());
	}
	
	@Test
	public void testGetDetailsByIdWithExistingCustomerReturnObject() {
		Customer customer = this.customerService.getDetailsById(SAVED_CUSTOMER_ID);
		assertNotNull(customer);
	}
	
	@Test
	public void testGetDetailsByIdWithNoneExistingCustomerReturnNull() {
		Customer customer = this.customerService.getDetailsById(UNSAVED_CUSTOMER_ID);
		assertNull(customer);
	}
	
	@Test
	public void testGetDetailsByIdWithExistingCustomerCallAccountMicroserviceGetCustomerAccountsOnce() {
		this.customerService.getDetailsById(SAVED_CUSTOMER_ID);
		verify(this.accountMicroserviceProxy, times(1)).getCustomerAccounts(this.validCustomer.getCustomerId());
	}

}
