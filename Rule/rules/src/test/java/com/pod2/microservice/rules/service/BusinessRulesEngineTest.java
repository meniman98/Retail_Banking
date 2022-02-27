package com.pod2.microservice.rules.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.pod2.microservice.rules.model.RuleStatus;

public class BusinessRulesEngineTest {
	
	public String ACCEPTED_STATUS = "allowed";
	public String REJECTED_STATUS = "denied";
	private float chargesFix = 10f;
	
	private BusinessRulesEngineImpl businessRulesEngine = new BusinessRulesEngineImpl();

	@Test
	public void testEvaluateWithAccountTypeSavingsAndBalanceLessThanZeroShouldReturnRejectedStatus() {
		RuleStatus ruleStatus = this.businessRulesEngine.evaluate(-0.1, "Savings");
		assertEquals(REJECTED_STATUS, ruleStatus.getStatus());
		ruleStatus = this.businessRulesEngine.evaluate(-50.0, "Savings");
		assertEquals(REJECTED_STATUS, ruleStatus.getStatus());
	}
	
	@Test
	public void testEvaluateWithAccountTypeSavingsAndBalanceEqualOrMoreThanZero() {
		RuleStatus ruleStatus = this.businessRulesEngine.evaluate(0.0, "Savings");
		assertEquals(businessRulesEngine.ACCEPTED_STATUS, ruleStatus.getStatus());
		ruleStatus = this.businessRulesEngine.evaluate(50.0, "Savings");
		assertEquals(ACCEPTED_STATUS, ruleStatus.getStatus());
	}
	
	@Test
	public void testEvaluateWithAccountTypeCurrentAndBalanceLessThanNegative50() {
		RuleStatus ruleStatus = this.businessRulesEngine.evaluate(-50.9, "Current");
		assertEquals(REJECTED_STATUS, ruleStatus.getStatus());
		ruleStatus = this.businessRulesEngine.evaluate(-100.0, "Current");
		assertEquals(REJECTED_STATUS, ruleStatus.getStatus());
	}
	
	@Test
	public void testEvaluateWithAccountTypeCurrentAndBalanceEqualOrMoreThanNegative50() {
		RuleStatus ruleStatus = this.businessRulesEngine.evaluate(-50.0, "Current");
		assertEquals(ACCEPTED_STATUS, ruleStatus.getStatus());
		ruleStatus = this.businessRulesEngine.evaluate(100.0, "Current");
		assertEquals(ACCEPTED_STATUS, ruleStatus.getStatus());
	}
	
	@Test
	public void testGetServiceChargesShouldReturnFixAmount() {
		assertEquals(this.chargesFix, this.businessRulesEngine.getServiceCharges());
	}
}
