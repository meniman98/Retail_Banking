package com.pod2.microservice.rules.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pod2.microservice.rules.model.RuleStatus;
import com.pod2.microservice.rules.service.BusinessRulesEngine;

@RestController
public class RulesRestController {
	
	@Autowired
	private BusinessRulesEngine businessRulesEngine;
	
	@GetMapping("${url.evaluate.rules}")
	public ResponseEntity<RuleStatus> evaluateMinBal(@RequestParam double balance, @RequestParam String accountType) {
		RuleStatus status = this.businessRulesEngine.evaluate(balance, accountType);
		return ResponseEntity.ok(status);
	}
	
	@GetMapping("${url.charges.rules}")
	public ResponseEntity<Float> getServiceCharges() {
		float charges = this.businessRulesEngine.getServiceCharges();
		return ResponseEntity.ok(charges);
	}
}
