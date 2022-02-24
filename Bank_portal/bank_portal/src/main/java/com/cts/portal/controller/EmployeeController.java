package com.cts.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {
	@GetMapping("/")
	public String homeAction() {
		return "home";
	}
	
	@GetMapping("/employee") 
	public String employeeHomepageAction() {
		return "employee";
	}
}
