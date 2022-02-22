package com.cts.portal.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cts.portal.model.Authority;
import com.cts.portal.model.BankUser;
import com.cts.portal.repository.BankUserRepository;

@Component
public class BankUserAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private BankUserRepository bankUserRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		BankUser user = this.bankUserRepo.findByEmail(username);
		if (null != user) {
			if (passwordEncoder.matches(password, user.getPassword())) {
				return new UsernamePasswordAuthenticationToken(username, password, this.getGrantedAuthorities(user.getAuthorities()));
			} else {
				throw new BadCredentialsException("Invalid password");
			}
		} else {
			throw new BadCredentialsException("No user registered with this details!");
		}
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (Authority authority : authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
		}
		return grantedAuthorities;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
