package com.cts.Account.repo;

import com.cts.Account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends
        JpaRepository<Account, Long> {
}
