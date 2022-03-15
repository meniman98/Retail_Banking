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

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

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

//    Tests for getSingleStatement()

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

//    Tests for getStatementListByDate()

    @Test
    void getStatementListByDate() {
//        initialise objects
        List<Statement> initialStatementList = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            Statement statement = new Statement();
            statement.setDate(LocalDate.of(2022, i, 1));
            initialStatementList.add(statement);
        }
        LocalDate jan = LocalDate.of(2022, 1, 1);
        LocalDate june = LocalDate.of(2022, 6, 1);
//        mocks
        Mockito.when(accountRepo.existsById(1L)).thenReturn(true);
        Mockito.when(statementRepo.findAllByDate(1L, jan, june))
                .thenReturn(initialStatementList);

        List<Statement> retrievedStatementList =
                statementService.getStatementListByDate(1L, jan, june);

        assertNotNull(retrievedStatementList);
        assertThat(retrievedStatementList, is(initialStatementList));
        assertThat(retrievedStatementList.size(), is(6));
        assertThat(retrievedStatementList.get(0).getDate().getMonth(),is(Month.JANUARY));
        assertThat(retrievedStatementList.get(2).getDate().getMonth(), is(Month.MARCH));


    }






}

