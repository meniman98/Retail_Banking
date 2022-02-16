package com.cts.Account.repo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends
        JpaRepository<AccountRepo, Long> {
}
