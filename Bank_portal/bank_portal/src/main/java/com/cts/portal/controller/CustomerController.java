package com.cts.portal.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.cts.portal.dto.Customer;
import com.cts.portal.dto.CustomerCreationStatus;
import com.cts.portal.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
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
	public String postCreateCustomerAction(ModelMap model, @Valid Customer customer, BindingResult result, RedirectAttributes attributes) {
		if (!result.hasErrors()) {
			CustomerCreationStatus status = this.customerService.createCustomer(customer);
			if (status.getMessage().equals("ko")) {
				model.addAttribute("status", status);
				return "create-customer";
			}
			attributes.addFlashAttribute("status", status);
			return "redirect:/getCustomer?firstName=" + customer.getFirstName();
		}
		return "create-customer";
	}
	
	@GetMapping("/getCustomer")
	public String getCustomerAction(ModelMap model, @RequestParam(required = false) String firstName) {
		if (firstName != null) {
			Customer customer = this.customerService.getCustomer(firstName);
			model.addAttribute("customer", customer);
		}
		return "customer-details";
	}
	
	@GetMapping("/customers")
	public String getListCustomer(ModelMap model) {
		List<Customer> customers = this.customerService.getCustomers();
		model.addAttribute("customers", customers);
		return "list-customer";
	}
}
