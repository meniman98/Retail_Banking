package com.cts.Account.service;

import com.cts.Account.Utils;
import com.cts.Account.model.Statement;
import com.cts.Account.repo.AccountRepo;
import com.cts.Account.repo.StatementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatementServiceImpl implements StatementService {

    StatementRepo statementRepo;

    AccountRepo accountRepo;

    @Autowired
    public StatementServiceImpl(AccountRepo accountRepo, StatementRepo statementRepo) {
        this.accountRepo = accountRepo;
        this.statementRepo = statementRepo;
    }

    @Override
    public List<Statement> getStatementListByDate(Long accountId, LocalDate startDate, LocalDate endDate) {
        if (accountRepo.existsById(accountId)) {
            List<Statement> statementList =
                    statementRepo.findAllByDate(accountId, startDate, endDate);
//            if (statementList.isEmpty()) {
////                Account ID exists but doesn't belong to any statement
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND, Utils.STATEMENT_NOT_FOUND);
//            }
            return statementList;
        } else if (!accountRepo.existsById(accountId)) {
//            This Account ID doesn't exist
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Utils.ACCOUNT_NOT_FOUND);
        } else {
//            Internal server error
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Statement getSingleStatement(Long accountId) {
        if (accountRepo.existsById(accountId)) {
            Statement statement = statementRepo.findByDate(accountId);
            if (statement == null) {
//                Account ID exists but doesn't belong to any statement
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, Utils.STATEMENT_NOT_FOUND);
            }
            return statement;
        }
        else {
//            This Account ID doesn't exist
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Utils.ACCOUNT_NOT_FOUND);
        }

    }
}
