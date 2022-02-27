package com.cts.portal.security;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.portal.dto.AuthToken;
import com.cts.portal.dto.LoginUser;

@FeignClient(name="${security.microservice.name}")
public interface SecurityRoleJwtMicroserviceProxy {
	@PostMapping("${security.microservice.register.path}")
	public LoginUser saveUser(@RequestBody LoginUser user);
	
	@PostMapping("${security.microservice.authenticate.path}")
	public AuthToken getToken(@RequestBody LoginUser user);
}
