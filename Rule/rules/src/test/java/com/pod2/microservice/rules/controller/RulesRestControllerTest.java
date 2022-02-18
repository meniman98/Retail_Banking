package com.pod2.microservice.rules.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.pod2.microservice.rules.model.RuleStatus;
import com.pod2.microservice.rules.service.BusinessRulesEngine;

@WebMvcTest(RulesRestController.class)
public class RulesRestControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BusinessRulesEngine businessRulesEngine;
	
	@Value("${url.evaluate.rules}")
	private String URL_EVALUATE_RULES;
	@Value("${url.charges.rules}")
	private String URL_CHARGES_RULES;
	@Value("${rules.charges.fix}")
	public float chargesFix = 10f;
	
	private RuleStatus ruleStatus = new RuleStatus("allowed");
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		// Mock BusinessRulesEngine
		Mockito.when(this.businessRulesEngine.evaluate(anyDouble(), anyString())).thenReturn(this.ruleStatus);
		Mockito.when(this.businessRulesEngine.getServiceCharges()).thenReturn(this.chargesFix);
	}
	
	@Test
	public void evaluateMinBalWithBalanceAndAccountTypeShouldReturnStatusOk() throws Exception {
		this.mockMvc.perform(get(this.URL_EVALUATE_RULES + "?balance=0&accountType=savings"))
		.andExpect(status().isOk());
	}
	@Test
	public void evaluateMinBalWithoutBalanceOrAccountTypeShouldReturnStatusBadRequest() throws Exception {
		this.mockMvc.perform(get(this.URL_EVALUATE_RULES + "?accountType=savings"))
		.andExpect(status().isBadRequest());
		this.mockMvc.perform(get(this.URL_EVALUATE_RULES + "?balance=10.5"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void evaluateMinBalWithBalanceAndAccountTypeShouldReturnRuleStatusObject() throws Exception {
		this.mockMvc.perform(get(this.URL_EVALUATE_RULES + "?balance=0&accountType=savings"))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value(this.ruleStatus.getStatus()));
	}
	
	@Test
	public void getServiceChargesShouldAlwaysReturnStatusOk() throws Exception {
		this.mockMvc.perform(get(this.URL_CHARGES_RULES))
		.andExpect(status().isOk());
	}
	
	@Test
	public void getServiceChargesShouldAlwaysReturnFixedAmount() throws Exception {
		this.mockMvc.perform(get(this.URL_CHARGES_RULES))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(content().string(this.chargesFix+""));
	}
}
