package com.pod2.microservice.customer.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pod2.microservice.customer.model.Customer;
import com.pod2.microservice.customer.model.CustomerCreationStatus;
import com.pod2.microservice.customer.service.CustomerService;

@RestController
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("${url.create.customer}")
	public ResponseEntity<CustomerCreationStatus> createCustomer(@RequestBody Customer customer) {
		CustomerCreationStatus status = this.customerService.create(customer);
		if (null != status) {
			//System.out.println(status);
			return ResponseEntity.ok(status);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("${url.details.customer}")
	public @ResponseBody Customer getCustomerDetails(@RequestParam Long customerId, HttpServletResponse httpResponse) {
		Customer customer = this.customerService.getDetailsById(customerId);
		if (null != customer) {
			httpResponse.setStatus(HttpStatus.OK.value());
		} else {
			httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
		}
		
		return customer;
	}
}
