package com.cts.portal.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.portal.dto.Customer;
import com.cts.portal.dto.CustomerCreationStatus;

@FeignClient(name="${customer.microservice.name}")
public interface CustomerMicroserviceProxy {
	@PostMapping("${customer.microservice.create.path}")
	public CustomerCreationStatus postCreateAccount(@RequestBody Customer customer, @RequestHeader(name = "Authorization", required = false) String bearerToken);
	
	@GetMapping("${customer.microservice.getDetails.path}")
	public Customer getCustomerDetails(@RequestParam Long customerId, @RequestHeader(name = "Authorization", required = false) String bearerToken);
	
	@GetMapping("${customer.microservice.getdetailsbyfirstname.path}")
	public Customer getCustomerDetailsByFirstName(@RequestParam String firstName, @RequestHeader(name = "Authorization", required = false) String bearerToken);
	
	@GetMapping("${customer.microservice.getallcustomer.path}")
	public List<Customer> getAllCustomerDetails(@RequestHeader(name = "Authorization", required = false) String bearerToken);
}
