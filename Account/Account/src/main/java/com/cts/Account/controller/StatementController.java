package com.cts.Account.controller;

import com.cts.Account.Utils;
import com.cts.Account.model.Statement;
import com.cts.Account.service.SecurityService;
import com.cts.Account.service.StatementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
public class StatementController {

    @Autowired
    StatementServiceImpl statementService;
    
    @Autowired
	private SecurityService securityService;

    @GetMapping(Utils.GET_STATEMENT_LIST)
    List<Statement> getStatementList(@PathVariable Long accountId,
                                     @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                     @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate, @RequestHeader(name = "Authorization", required = false) String bearerToken) {
    	if (securityService.isMember(bearerToken)) {
    		return statementService.getStatementListByDate(accountId, startDate, endDate);
    	}
    	throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only members can access this resource.");
    }

    @GetMapping(Utils.GET_SINGLE_STATEMENT)
    Statement getSingleStatement(@PathVariable Long accountId, @RequestHeader(name = "Authorization", required = false) String bearerToken) {
    	if (securityService.isMember(bearerToken)) {
    		return statementService.getSingleStatement(accountId);
    	}
    	throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only members can access this resource.");
    }



}
