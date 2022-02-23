package com.cts.Account.service;

import com.cts.Account.model.Statement;
import com.cts.Account.repo.StatementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatementServiceImpl implements StatementService{

    @Autowired
    StatementRepo statementRepo;

    @Override
    public List<Statement> getStatementListByDate(Long accountId, LocalDate startDate, LocalDate endDate) {
        return statementRepo.findAllByDate(accountId, startDate, endDate);
    }

    @Override
    public Statement getSingleStatement(Long accountId) {
        return statementRepo.findByDate(accountId);
    }
}
