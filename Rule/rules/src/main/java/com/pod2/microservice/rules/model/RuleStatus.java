package com.pod2.microservice.rules.model;

import lombok.Data;

@Data
public class RuleStatus {
	private String status;
	
	public RuleStatus() {
		
	}
	
	public RuleStatus(String status) {
		this();
		this.status = status;
	}
}
