package com.cts.Account.controller;

import com.cts.Account.Utils;
import com.cts.Account.model.Statement;
import com.cts.Account.service.StatementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class StatementController {

    @Autowired
    StatementServiceImpl statementService;

    @GetMapping(Utils.GET_STATEMENT_LIST)
    List<Statement> getStatementList(@PathVariable Long accountId,
                                     @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                     @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return statementService.getStatementListByDate(accountId, startDate, endDate);
    }

    @GetMapping(Utils.GET_SINGLE_STATEMENT)
    Statement getSingleStatement(@PathVariable Long accountId) {
        return statementService.getSingleStatement(accountId);
    }



}
