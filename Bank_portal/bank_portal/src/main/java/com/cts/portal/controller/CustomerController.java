package com.cts.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {
	@GetMapping("/createCustomer")
	public String getCreateCustomerAction() {
		return "create-customer";
	}
	
	@GetMapping("/getCustomer")
	public String getCustomerAction() {
		return "customer-details";
	}
	
	@GetMapping("/customers")
	public String getListCustomer() {
		return "list-customer";
	}
}
