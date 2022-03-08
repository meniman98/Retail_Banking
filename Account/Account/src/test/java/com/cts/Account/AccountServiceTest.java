package com.cts.Account;

import com.cts.Account.model.Account;
import com.cts.Account.model.AccountCreationStatus;
import com.cts.Account.repo.AccountRepo;
import com.cts.Account.service.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    AccountRepo accountRepo;

    @InjectMocks
    AccountServiceImpl accountService;

    @Test
    void createAccountSuccess() {
        AccountCreationStatus status = accountService.createAccount(42L);
        // check the "contract" returns what we expect
        assertNotNull(status);
        assertEquals("Account successfully created", status.getMessage());

        // check that mock was invoked twice
        Mockito.verify(accountRepo, Mockito.times(2)).save(Mockito.any());

        // check that a savings and a current account were saved
        Mockito.verify(accountRepo, Mockito.atLeastOnce()).save(Mockito.argThat(account -> account.getAccountType().equals("Savings")));
        Mockito.verify(accountRepo, Mockito.atLeastOnce()).save(Mockito.argThat(account -> account.getAccountType().equals("Current")));
    }

    @Test
    void failureCreateAccount() {
        Mockito.when(accountRepo.save(Mockito.any())).thenThrow(new DuplicateKeyException("crash"));
        // FIXME original ahmed code
        // Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.createAccount(1L));
        AccountCreationStatus status = accountService.createAccount(1L);
        assertNotNull(status);
        assertEquals("duplicated account id", status.getMessage());
    }

    @Test
    void getCustomerAccount() {
        Account expectedAccount = new Account(1L, 5L);
        Mockito.when(accountRepo.getById(1L)).thenReturn(expectedAccount);
        Mockito.when(accountRepo.existsById(1L)).thenReturn(true);

        Account acc = accountService.getAccount(1L);
        assertSame(expectedAccount, acc);
        Mockito.verify(accountRepo, Mockito.atMostOnce()).getById(1L);
    }

    @Test
    void getCustomerAccountFails() {
        Mockito.when(accountRepo.existsById(1L)).thenReturn(false);
        Assertions.assertThrows(ResponseStatusException.class, () -> accountService.getAccount(1L));
    }

    @Test
    void getCustomerAccountList() {
        List<Account> retrievedAccounts = Arrays.asList(new Account(), new Account());
        Mockito.when(accountRepo.findByCustomerId(1L)).thenReturn(retrievedAccounts);
        List<Account> list = accountService.getCustomerAccounts(1L);
        assertSame(retrievedAccounts, list);
    }

    @Test
    void getCustomerAccountListEmpty() {
        List<Account> emptyAccounts = Collections.emptyList();
        Mockito.when(accountRepo.findByCustomerId(1L)).thenReturn(emptyAccounts);
        Assertions.assertThrows(ResponseStatusException.class, () -> accountService.getCustomerAccounts(1L));
    }

}
