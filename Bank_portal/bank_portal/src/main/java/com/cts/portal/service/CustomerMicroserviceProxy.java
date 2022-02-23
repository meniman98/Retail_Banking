package com.cts.portal.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.portal.dto.Customer;
import com.cts.portal.dto.CustomerCreationStatus;

@FeignClient(name="${customer.microservice.name}", url="${customer.microservice.url}")
public interface CustomerMicroserviceProxy {
	@PostMapping("${customer.microservice.create.path}")
	public CustomerCreationStatus postCreateAccount(@RequestBody Customer customer);
	
	@GetMapping("${customer.microservice.getDetails.path}")
	public Customer getCustomerDetails(@RequestParam Long customerId);
	
	@GetMapping("${customer.microservice.getdetailsbyfirstname.path}")
	public Customer getCustomerDetailsByFirstName(@RequestParam String firstName);
	
	@GetMapping("${customer.microservice.getallcustomer.path}")
	public List<Customer> getAllCustomerDetails();
}
