package com.cts.Account.service;

import com.cts.Account.model.Statement;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.ws.rs.Path;
import java.time.LocalDate;
import java.util.List;

@Service
public interface StatementService {

    List<Statement> getStatementListByDate(@PathVariable Long accountId,
                                           @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                           @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate);

    Statement getSingleStatement(@PathVariable Long accountId);
}
