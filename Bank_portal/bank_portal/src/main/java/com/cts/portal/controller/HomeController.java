package com.cts.portal.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/login")
	public String loginAction(ModelMap model, String error, String logout) {
		if (error != null) {
			model.addAttribute("errorMsg", "Invalid credentials");
		}
		return "login";
	}
	
	@GetMapping("/loginSuccess")
	public String loginSuccessAction(Principal user) {
		return "employee";
	}
	
	@GetMapping("/employee")
	public String employeeHomepageAction() {
		return "employee";
	}
}
