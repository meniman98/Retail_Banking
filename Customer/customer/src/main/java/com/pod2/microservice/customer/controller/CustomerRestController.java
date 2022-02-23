package com.pod2.microservice.customer.controller;

import java.util.List;

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
	
	@PostMapping("${create.customer.url}")
	public ResponseEntity<CustomerCreationStatus> createCustomer(@RequestBody Customer customer) {
		CustomerCreationStatus status = this.customerService.create(customer);
		if (null != status) {
			//System.out.println(status);
			return ResponseEntity.ok(status);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("${details.customer.url}")
	public ResponseEntity<Customer> getCustomerDetails(@RequestParam Long customerId) {
		Customer customer = this.customerService.getDetailsById(customerId);
		if (null != customer) {
			return ResponseEntity.ok(customer);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("${details.customer.byfirstname.url}")
	public ResponseEntity<Customer> getCustomerDetails(@RequestParam String firstName) {
		Customer customer = this.customerService.getDetailsByFirstName(firstName);
		if (null != customer) {
			return ResponseEntity.ok(customer);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("${details.all.customer.url}")
	public ResponseEntity<List<Customer>> getAllCustomerDetails() {
		List<Customer> customers = this.customerService.getAllCustomerDetails();
		if (!customers.isEmpty()) {
			return ResponseEntity.ok(customers);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
