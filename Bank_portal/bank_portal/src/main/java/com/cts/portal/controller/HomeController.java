package com.cts.portal.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cts.portal.dto.AccountSummary;
import com.cts.portal.dto.Customer;
import com.cts.portal.model.BankUser;
import com.cts.portal.service.CustomerService;

@Controller
@SessionAttributes({"user", "accountsNo"})
public class HomeController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/dashboard")
	public String customerHomepageAction(ModelMap model, @ModelAttribute("user") BankUser user) {
		Customer customer = this.customerService.getCustomer(user.getCustomerFirstName());
		model.addAttribute("customer", customer);
		return "dashboard";
	}
	
	@GetMapping("/operation")
	public String getOperationAction(ModelMap model, @ModelAttribute("accountsNo") List<String> accountsNo) {
		model.addAttribute("accountsNo", accountsNo);
		return "operation";
	}
	
	@ModelAttribute("user")
	public BankUser getCustomer(Principal userPrincipal) {
		return this.customerService.getUser(userPrincipal.getName());
	}
	
	@ModelAttribute("accountsNo")
	public List<String> getAccountsNo(ModelMap model, @ModelAttribute("user") BankUser user) {
		List<String> accountsNo = new ArrayList<>();
		Customer customer = this.customerService.getCustomer(user.getCustomerFirstName());
		for (AccountSummary account : customer.getAccountsSummary()) {
			accountsNo.add(account.getAccountNo());
		}
		return accountsNo;
	}
}
