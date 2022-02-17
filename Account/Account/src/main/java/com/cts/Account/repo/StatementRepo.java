package com.cts.Account.repo;

import com.cts.Account.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StatementRepo extends JpaRepository<Statement, Long> {

    @Query("select s from Statement" +
            "where s.date between ?1 and ?2;")
    Statement getStatementByDate(String startDate, String endDate);

}
