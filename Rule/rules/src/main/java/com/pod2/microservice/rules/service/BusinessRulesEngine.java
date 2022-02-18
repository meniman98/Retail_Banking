package com.pod2.microservice.rules.service;

import com.pod2.microservice.rules.model.RuleStatus;

public interface BusinessRulesEngine {
	public String ACCEPTED_STATUS = "allowed";
	public String REJECTED_STATUS = "denied";
	
	public RuleStatus evaluate(double balance, String accountType);
	public float getServiceCharges();
}
