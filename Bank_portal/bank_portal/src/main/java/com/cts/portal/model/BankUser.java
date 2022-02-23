package com.cts.portal.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class BankUser {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private Long customerId;
	private String email;
	private String password;
	private String role;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.EAGER)
	private Set<Authority> authorities;
}
