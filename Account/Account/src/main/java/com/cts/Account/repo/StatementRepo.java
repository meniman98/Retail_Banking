package com.cts.Account.repo;

import com.cts.Account.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StatementRepo extends JpaRepository<Statement, Long> {

    @Query(value = "SELECT * FROM statement " +
            "WHERE account_id = :accountId AND date BETWEEN :startDate AND :endDate " +
            "ORDER BY statement_id DESC",
    nativeQuery = true)
    List<Statement> findAllByDate(Long accountId, LocalDate startDate, LocalDate endDate);

    @Query(value = "SELECT * FROM statement " +
            "WHERE account_id = :accountId " +
            "ORDER BY Date DESC LIMIT 1",
            nativeQuery = true)
    Statement findByDate(@Param("accountId") Long accountId);

}
