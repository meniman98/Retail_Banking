package com.cts.Account.repo;

import com.cts.Account.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatementRepo extends
        JpaRepository<Statement, Long> {
}
