package com.pod2.microservice.customer.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pod2.microservice.customer.model.Customer;
import com.pod2.microservice.customer.model.CustomerCreationStatus;
import com.pod2.microservice.customer.service.CustomerService;

@RestController
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/createCustomer")
	public @ResponseBody CustomerCreationStatus createCustomer(@RequestBody Customer customer, HttpServletResponse httpResponse) {
		CustomerCreationStatus status = this.customerService.create(customer);
		if (null != status) {
			httpResponse.setStatus(HttpStatus.CREATED.value());
		}
		return status;
	}
}
