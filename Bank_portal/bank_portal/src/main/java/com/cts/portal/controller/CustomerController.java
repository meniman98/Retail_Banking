package com.cts.portal.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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

import com.cts.portal.dto.AuthToken;
import com.cts.portal.dto.Customer;
import com.cts.portal.dto.CustomerCreationStatus;
import com.cts.portal.dto.LoginUser;
import com.cts.portal.model.BankUser;
import com.cts.portal.security.SecurityService;
import com.cts.portal.service.CustomerService;

@Controller
@SessionAttributes({"user", "bearerToken" })
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SecurityService securityService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@ModelAttribute("user")
	public BankUser getCustomer(Principal userPrincipal) {
		return this.customerService.getUser(userPrincipal.getName());
	}
	
	@ModelAttribute("bearerToken")
	public String getBearerToken(@ModelAttribute("user") BankUser user, Principal principal) {
		LoginUser login = new LoginUser();
		login.setUsername(user.getEmail());
		login.setPassword(user.getPassword());
		String token = this.securityService.getToken(login);
		if (token == null) {
			throw new BadCredentialsException("No user registered as " + login.getUsername());
		}
		return "Bearer " + token;
	}
	
	@GetMapping("/createCustomer")
	public String getCreateCustomerAction(ModelMap model, Customer customer) {
		return "create-customer";
	}
	
	@PostMapping("/createCustomer")
	public String postCreateCustomerAction(ModelMap model, @Valid Customer customer, BindingResult result, RedirectAttributes attributes, @ModelAttribute("bearerToken") String bearerToken) {
		if (!result.hasErrors()) {
			CustomerCreationStatus status = this.customerService.createCustomer(customer, bearerToken);
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
	public String getCustomerAction(ModelMap model, @RequestParam(required = false) String firstName, @ModelAttribute("bearerToken") String bearerToken) {
		if (firstName != null) {
			Customer customer = this.customerService.getCustomer(firstName, bearerToken);
			model.addAttribute("customer", customer);
		}
		return "customer-details";
	}
	
	@GetMapping("/customers")
	public String getListCustomer(ModelMap model, @ModelAttribute("bearerToken") String bearerToken) {
		if (bearerToken.isEmpty())
			System.out.println("Bearer EMPTY");
		System.out.println(bearerToken);
		List<Customer> customers = this.customerService.getCustomers(bearerToken);
		model.addAttribute("customers", customers);
		return "list-customer";
	}
}
