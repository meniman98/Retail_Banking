package com.pod2.microservice.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pod2.microservice.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByCustomerId(Long customerId);
}
