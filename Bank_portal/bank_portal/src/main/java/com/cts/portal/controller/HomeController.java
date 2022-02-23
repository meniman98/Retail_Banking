package com.cts.portal.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String homeAction() {
		return "home";
	}
	
	
	@GetMapping("/employee")
	public String employeeHomepageAction() {
		return "employee";
	}
}
