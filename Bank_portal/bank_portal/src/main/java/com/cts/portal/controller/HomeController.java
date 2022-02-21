package com.cts.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/login")
	public String loginAction() {
		return "login";
	}
	
	@GetMapping("/employee")
	public String employeeHomepageAction() {
		return "employee";
	}
}
