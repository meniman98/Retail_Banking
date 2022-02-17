package com.pod2.microservice.customer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class CustomerCreationStatus {
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String message;

	public CustomerCreationStatus() {
		super();
	}

	public CustomerCreationStatus(String message) {
		this();
		this.message = message;
	}

	public CustomerCreationStatus(Long id, String message) {
		super();
		this.id = id;
		this.message = message;
	}

}
