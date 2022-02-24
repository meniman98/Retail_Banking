package com.cts.Account.repo;

import com.cts.Account.model.Account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepo extends JpaRepository<Account, Long> {
	List<Account> findByCustomerId(Long customerId);

	@Query(value = "SELECT * FROM account WHERE account_no = :account_no",
			nativeQuery = true)
	Account findByAccountNo(@Param("account_no") String accountNo);
}
