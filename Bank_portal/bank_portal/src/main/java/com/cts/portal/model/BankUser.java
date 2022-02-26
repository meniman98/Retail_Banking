package com.cts.portal.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(name="seq", initialValue=2)
public class BankUser {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private int id;
	private Long customerId;
	private String customerFirstName;
	private String email;
	@JsonIgnore
	private String password;
	private String role;
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Authority> authorities;
}
