package com.cts.Account;

import com.cts.Account.model.Statement;
import com.cts.Account.repo.AccountRepo;
import com.cts.Account.repo.StatementRepo;
import com.cts.Account.service.StatementService;
import com.cts.Account.service.StatementServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StatementServiceTest {

    @Mock
    AccountRepo accountRepo;

    @Mock
    StatementRepo statementRepo;

    @InjectMocks
    StatementServiceImpl statementService;

    @Test
    void getSingleStatement() {
        Mockito.when(accountRepo.existsById(1L))
                .thenReturn(true);

        Mockito.when(statementRepo.findByDate(1L))
                .thenReturn(new Statement());

        statementService.getSingleStatement(1L);
    }
}
