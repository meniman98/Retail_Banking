package com.cts.transaction.repo;

import com.cts.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountID(Long accountID);
}
