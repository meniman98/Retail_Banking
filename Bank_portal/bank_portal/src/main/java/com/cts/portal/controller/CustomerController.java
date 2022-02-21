package com.cts.portal.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import com.cts.portal.dto.Customer;

@Controller
public class CustomerController {
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@GetMapping("/createCustomer")
	public String getCreateCustomerAction(ModelMap model, Customer customer) {
		return "create-customer";
	}
	
	@PostMapping("/createCustomer")
	public String postCreateCustomerAction(ModelMap model, @Valid Customer customer, BindingResult result) {
		if (result.hasErrors()) {
			return "create-customer";
		}
		return "redirect:/customers";
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
