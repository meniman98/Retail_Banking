package com.cts.Account;

import com.cts.Account.model.Statement;
import com.cts.Account.repo.AccountRepo;
import com.cts.Account.service.StatementService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StatementServiceTest {

    @Autowired
    StatementService statementService;

    @Mock
    AccountRepo accountRepo;

    @Test
    void getSingleStatement() {
//        Mockito.when(statementService.getSingleStatement(1L))
//                .thenReturn(new Statement());

        statementService.getSingleStatement(1L);
    }
}
