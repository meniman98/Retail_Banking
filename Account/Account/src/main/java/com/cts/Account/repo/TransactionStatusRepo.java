package com.cts.Account.repo;

import com.cts.Account.model.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionStatusRepo extends
        JpaRepository<TransactionStatus, Long> {
}
