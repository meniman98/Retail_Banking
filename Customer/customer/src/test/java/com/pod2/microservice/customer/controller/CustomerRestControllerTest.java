package com.pod2.microservice.customer.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.pod2.microservice.customer.model.Customer;
import com.pod2.microservice.customer.model.CustomerCreationStatus;
import com.pod2.microservice.customer.service.CustomerService;

@WebMvcTest(CustomerRestController.class)
public class CustomerRestControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerService customerService;
	
	@Autowired
	private Gson gson;

	@Value("${url.create.customer}")
	private String URL_CREATE_CUSTOMER;
	@Value("${url.details.customer}")
	private String URL_DETAILS_CUSTOMER;
	
	private Customer validCustomer = new Customer(1L, "Test", "Test", "test@test.com", "0123456789", "1, Test France",
			"01/01/1998");
	private Customer invalidCustomer = new Customer(0L, "", "", "", "", "1, Test France", "01/01/1998");
	private CustomerCreationStatus customerCreationStatus = new CustomerCreationStatus(1L, this.customerService.MSG_CUSTOMER_CREATION_SUCCESS);
	
	private final Long SAVED_CUSTOMER_ID = 1L;
	private final Long UNSAVED_CUSTOMER_ID = 5L;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		// Mock CustomerService
		Mockito.when(this.customerService.create(this.validCustomer)).thenReturn(this.customerCreationStatus);
		Mockito.when(this.customerService.create(this.invalidCustomer)).thenReturn(null);		
		Mockito.when(this.customerService.getDetailsById(this.SAVED_CUSTOMER_ID)).thenReturn(this.validCustomer);		
		Mockito.when(this.customerService.getDetailsById(this.UNSAVED_CUSTOMER_ID)).thenReturn(null);		
	}
	
	@Test
	public void createCustomerWithValidCustomerShouldReturnIsCreatedStatus() throws Exception {
		String customerJson = this.gson.toJson(this.validCustomer);
		this.mockMvc.perform(post(this.URL_CREATE_CUSTOMER)
				.contentType(MediaType.APPLICATION_JSON)
				.content(customerJson).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void createCustomerWithInvalidCustomerShouldReturnBadRequestStatus() throws Exception {
		String invalidCustomerJson = this.gson.toJson(this.invalidCustomer);
		this.mockMvc.perform(post(this.URL_CREATE_CUSTOMER)
				.contentType(MediaType.APPLICATION_JSON)
				.content(invalidCustomerJson).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void createCustomerWithValidCustomerShouldReturnCreationStatusObject() throws Exception {
		String customerJson = this.gson.toJson(this.validCustomer);
		this.mockMvc.perform(post(this.URL_CREATE_CUSTOMER)
				.contentType(MediaType.APPLICATION_JSON)
				.content(customerJson).accept(MediaType.APPLICATION_JSON))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.message").value(this.customerCreationStatus.getMessage()));
	}
	
	@Test
	public void getCustomerDetailsWithExistingCustomerShouldReturnStatusOk() throws Exception {
		this.mockMvc.perform(get(this.URL_DETAILS_CUSTOMER + "?customerId=" + this.SAVED_CUSTOMER_ID))
		.andExpect(status().isOk());
	}
	
	@Test
	public void getCustomerDetailsWithNoneExistingCustomerShouldReturnStatusNotFound() throws Exception {
		this.mockMvc.perform(get(this.URL_DETAILS_CUSTOMER + "?customerId=" + this.UNSAVED_CUSTOMER_ID))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void getCustomerDetailsWithExistingCustomerShouldReturnCustomerObject() throws Exception {
		this.mockMvc.perform(get(this.URL_DETAILS_CUSTOMER + "?customerId=" + this.SAVED_CUSTOMER_ID))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.customerId").value(this.validCustomer.getCustomerId()))
		.andExpect(jsonPath("$.dateOfBirth").value(this.validCustomer.getDateOfBirth()))
		.andExpect(jsonPath("$.address").value(this.validCustomer.getAddress()))
		.andExpect(jsonPath("$.mobileNumber").value(this.validCustomer.getMobileNumber()))
		.andExpect(jsonPath("$.email").value(this.validCustomer.getEmail()))
		.andExpect(jsonPath("$.lastName").value(this.validCustomer.getLastName()))
		.andExpect(jsonPath("$.firstName").value(this.validCustomer.getFirstName()));
	}
}
