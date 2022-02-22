package com.cts.portal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.portal.model.BankUser;

@Repository
public interface BankUserRepository extends CrudRepository<BankUser, Long> {
	BankUser findByEmail(String email);
}
