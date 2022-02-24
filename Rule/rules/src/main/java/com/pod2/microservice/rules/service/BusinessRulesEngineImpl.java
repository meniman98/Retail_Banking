package com.pod2.microservice.rules.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pod2.microservice.rules.model.RuleStatus;

@Service
public class BusinessRulesEngineImpl implements BusinessRulesEngine {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Value("${rules.status.accepted:allowed}")
	public String ACCEPTED_STATUS = "allowed";
	@Value("${rules.status.rejected:denied}")
	public String REJECTED_STATUS = "denied";
	@Value("${rules.charges.fix}")
	public float chargesFix = 10f;
	
	private Map<String, Double> minBalanceAccountTypeMap;

	public BusinessRulesEngineImpl() {
		this.minBalanceAccountTypeMap = new HashMap<>();
		this.minBalanceAccountTypeMap.put("Savings", 0.0);
		this.minBalanceAccountTypeMap.put("Current", -50.0);
	}
	
	@Override
	public RuleStatus evaluate(double balance, String accountType) {
		if (!this.minBalanceAccountTypeMap.keySet().contains(accountType)) {
			this.log.info("Rejected evaluation for balance = " + balance + " and accountType = " + accountType);
			return new RuleStatus(this.REJECTED_STATUS);
		}
		if (balance >= this.minBalanceAccountTypeMap.get(accountType)) {
			this.log.info("Accepted evaluation for balance = " + balance + " and accountType = " + accountType);
			return new RuleStatus(this.ACCEPTED_STATUS);
		}
		this.log.info("Rejected evaluation for balance = " + balance + " and accountType = " + accountType);
		return new RuleStatus(this.REJECTED_STATUS);
	}

	@Override
	public float getServiceCharges() {
		this.log.info("Send service charges = " + this.chargesFix);
		return this.chargesFix;
	}

}
