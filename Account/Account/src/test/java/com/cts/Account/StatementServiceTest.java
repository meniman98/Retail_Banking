package com.cts.Account;

import com.cts.Account.model.Statement;
import com.cts.Account.service.StatementService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StatementServiceTest {

    @Mock
    StatementService statementService;

    @Test
    void getSingleStatement() {
        Mockito.when(statementService.getSingleStatement(1L))
                .thenReturn(new Statement());

        statementService.getSingleStatement(1L);
    }
}
