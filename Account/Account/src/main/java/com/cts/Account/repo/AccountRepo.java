package com.cts.Account.repo;

import com.cts.Account.model.Account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends
        JpaRepository<Account, Long> {
	List<Account> findByCustomerId(Long customerId);
}
