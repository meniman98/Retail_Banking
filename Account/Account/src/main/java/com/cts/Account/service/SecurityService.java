package com.cts.Account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
	@Autowired
	private SecurityRoleJwtMicroserviceProxy securityProxy;
	
	public boolean isMember(String bearerToken) {
		return this.hasCustomerRole(bearerToken) || this.hasEmployeeRole(bearerToken);
	}
	
	public boolean hasEmployeeRole(String bearerToken) {
		try {
			return this.securityProxy.employeePing(bearerToken);
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean hasCustomerRole(String bearerToken) {
		try {
			return this.securityProxy.customerPing(bearerToken);
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
