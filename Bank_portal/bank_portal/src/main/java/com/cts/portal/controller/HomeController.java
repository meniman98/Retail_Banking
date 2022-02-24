package com.cts.portal.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cts.portal.dto.AccountSummary;
import com.cts.portal.dto.Customer;
import com.cts.portal.dto.CustomerCreationStatus;
import com.cts.portal.dto.Operation;
import com.cts.portal.dto.Transaction;
import com.cts.portal.dto.TransactionStatus;
import com.cts.portal.model.BankUser;
import com.cts.portal.service.CustomerService;
import com.cts.portal.service.TransactionService;

@Controller
@SessionAttributes({"user", "accounts"})
public class HomeController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private TransactionService transactionService;
	
	
	@GetMapping("/dashboard")
	public String customerHomepageAction(ModelMap model, @ModelAttribute("user") BankUser user) {
		Customer customer = this.customerService.getCustomer(user.getCustomerFirstName());
		model.addAttribute("customer", customer);
		return "dashboard";
	}
	
	@GetMapping("/operation")
	public String getOperationAction(ModelMap model, Operation operation, @ModelAttribute("accounts") List<AccountSummary> accounts) {
		model.addAttribute("accounts", accounts);
		return "operation";
	}
	
	@GetMapping("/transactions")
	public String getTransactionAction(ModelMap model, @ModelAttribute("user") BankUser user) {
		Customer customer = this.customerService.getCustomer(user.getCustomerFirstName());
		List<Transaction> transactions = this.transactionService.getTransactions(customer.getCustomerId());
		model.addAttribute("transactions", transactions);
		return "list-transaction";
	}
	
	@PostMapping("/operation")
	public String postOperation(ModelMap model, @Valid Operation operation, BindingResult result, RedirectAttributes attributes) {
		if (!result.hasErrors()) {
			TransactionStatus status = this.transactionService.postOperation(operation);
			if (status != null) {
				attributes.addFlashAttribute("status", status);
				return "redirect:/dashboard";
			}
			model.addAttribute("status", "ko");
		}
		return "operation";
	}
	
	@ModelAttribute("user")
	public BankUser getCustomer(Principal userPrincipal) {
		return this.customerService.getUser(userPrincipal.getName());
	}
	
	@ModelAttribute("accounts")
	public List<AccountSummary> getAccountsNo(ModelMap model, @ModelAttribute("user") BankUser user) {
		Customer customer = this.customerService.getCustomer(user.getCustomerFirstName());
		return customer.getAccountsSummary();
	}
}
