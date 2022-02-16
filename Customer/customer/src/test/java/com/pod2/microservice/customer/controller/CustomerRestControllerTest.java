package com.pod2.microservice.customer.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.pod2.microservice.customer.model.Customer;
import com.pod2.microservice.customer.model.CustomerCreationStatus;
import com.pod2.microservice.customer.service.CustomerService;
import com.pod2.microservice.customer.service.CustomerServiceImpl;

@WebMvcTest(CustomerRestController.class)
public class CustomerRestControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerService customerService;
	
	@Autowired
	private Gson gson;

	
	private Customer validCustomer = new Customer(1L, "Test", "Test", "test@test.com", "0123456789", "1, Test France",
			"01/01/1998");
	private Customer invalidCustomer = new Customer(0L, "", "", "", "", "1, Test France", "01/01/1998");
	private CustomerCreationStatus customerCreationStatus = new CustomerCreationStatus(1L, CustomerServiceImpl.MSG_CUSTOMER_CREATION_SUCCESS);
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		// Mock CustomerService
		Mockito.when(this.customerService.create(this.validCustomer)).thenReturn(this.customerCreationStatus);
	}
	
//	@Test
//	public void createCustomerWithValidCustomerShouldReturnOkStatus() throws Exception {
//		this.mockMvc.perform(post("/createCustomer").contentType(MediaType.APPLICATION_JSON).content().andDo(print()).andExpect(status().isOk());
//	}
	
	@Test
	public void createCustomerWithValidCustomerShouldReturnObject() throws Exception {
		String customerJson = this.gson.toJson(this.validCustomer);
		this.mockMvc.perform(post("/createCustomer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(customerJson).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").value(this.customerCreationStatus.getId()))
		.andExpect(jsonPath("$.message").value(this.customerCreationStatus.getMessage()));
	}
}
