package com.cts.Account.repo;

import com.cts.Account.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StatementRepo extends JpaRepository<Statement, Long> {

//    FIXME: create this query correctly. it throws an exception
//    @Query("select * from statement " +
//            "where date between ?1 and ?2")
//    Statement findStatementByDate(String startDate, String endDate);

}
