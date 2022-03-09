package com.cts.Account;

import com.cts.Account.model.Statement;
import com.cts.Account.repo.AccountRepo;
import com.cts.Account.repo.StatementRepo;
import com.cts.Account.service.StatementServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StatementServiceTest {

    @Mock
    AccountRepo accountRepo;

    @Mock
    StatementRepo statementRepo;

    @InjectMocks
    StatementServiceImpl statementService;

    @Test
    void getSingleStatement() {
        Statement repoStatement = new Statement();

        Mockito.when(accountRepo.existsById(1L))
                .thenReturn(true);

        Mockito.when(statementRepo.findByDate(1L))
                .thenReturn(repoStatement);

        Statement retrievedStatement = statementService.getSingleStatement(1L);
        assertSame(repoStatement, retrievedStatement);
    }

    @Test
    void accountExistsButDoesNotBelongToAnyStatement() {
        Mockito.when(accountRepo.existsById(1L)).thenReturn(true);
        Mockito.when(statementRepo.findByDate(1L)).thenReturn(null);

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> {
                   statementService.getSingleStatement(1L);
                });

        assertThat(exception.getReason(),is(Utils.STATEMENT_NOT_FOUND));
        assertThat(exception.getRawStatusCode(),is(404));
    }

    @Test
    void accountDoesNotExist() {
        Mockito.when(accountRepo.existsById(1L)).thenReturn(false);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                statementService.getSingleStatement(1L));

        assertThat(exception.getReason(),is(Utils.ACCOUNT_NOT_FOUND));
        assertThat(exception.getRawStatusCode(),is(404));
    }




}

