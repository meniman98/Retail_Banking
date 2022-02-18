package com.pod2.microservice.rules.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.pod2.microservice.rules.model.RuleStatus;

public class BusinessRulesEngineImpl implements BusinessRulesEngine {
	@Value("${rules.status.accepted:allowed}")
	public String ACCEPTED_STATUS = "allowed";
	@Value("${rules.status.rejected:denied}")
	public String REJECTED_STATUS = "denied";
	
	private Map<String, Double> minBalanceAccountTypeMap;

	public BusinessRulesEngineImpl() {
		this.minBalanceAccountTypeMap = new HashMap<>();
		this.minBalanceAccountTypeMap.put("savings", 0.0);
		this.minBalanceAccountTypeMap.put("current", -50.0);
	}
	
	@Override
	public RuleStatus evaluate(double balance, String accountType) {
		if (!this.minBalanceAccountTypeMap.keySet().contains(accountType)) {
			return new RuleStatus(this.REJECTED_STATUS);
		}
		if (balance >= this.minBalanceAccountTypeMap.get(accountType)) {
			return new RuleStatus(this.ACCEPTED_STATUS);
		}
		return new RuleStatus(this.REJECTED_STATUS);
	}

	@Override
	public float getServiceCharges() {
		// TODO Auto-generated method stub
		return 0;
	}

}
