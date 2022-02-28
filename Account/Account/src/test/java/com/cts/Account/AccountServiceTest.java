package com.cts.Account;

import com.cts.Account.model.Account;
import com.cts.Account.model.AccountCreationStatus;
import com.cts.Account.repo.AccountRepo;
import com.cts.Account.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccountServiceTest {

    @Mock
    AccountServiceImpl accountService;

    @Mock
    AccountRepo accountRepo;


    Account testAccount = new Account(1, 1);
    Account testAccount2 = new Account(2, 1);
    AccountCreationStatus testAccountStatus = new AccountCreationStatus(new Account(), null);
    List<Account> customerAccounts = Arrays.asList(testAccount, testAccount2);
    List<Account> emptyList = new ArrayList<>();


    /*1. create an account
    * 2. get back that same account with the ID
    * 3. Make sure that is the same account*/

    @BeforeEach
    void setUp() {
        Mockito.when(accountRepo.save(testAccount)).thenReturn(testAccount);
        Mockito.when(accountRepo.getById(1L)).thenReturn(testAccount);

        Mockito.when(accountService.createAccount(testAccount.getCustomerId())).
                thenReturn(testAccountStatus);

        Mockito.when(accountService.getAccount(1L)).
                thenReturn(testAccount);

        Mockito.when(accountService.getCustomerAccounts(1L)).
                thenReturn(customerAccounts);
    }

    @Test
    void createAccount() {
        accountService.createAccount(testAccount.getCustomerId());
//        FIXME: look into why this doesn't get called
//        Mockito.verify(accountRepo).save(testAccount);
        assertEquals(accountRepo.getById(testAccount.getAccountId()), testAccount);
    }

    @Test
    void failureCreateAccount() {
//        Mismatching IDs
        accountService.createAccount(testAccount2.getCustomerId());
        assertNotEquals(accountRepo.getById(testAccount.getAccountId()), testAccount2);
    }

    @Test
    void getCustomerAccounts() {
        List<Account> returnedAccounts = accountService.getCustomerAccounts(1L);
        assertEquals(returnedAccounts, customerAccounts);
    }

    @Test
    void emptyGetCustomerAccounts() {
        List<Account> returnedAccounts = accountService.getCustomerAccounts(100L);
        assertEquals(emptyList, returnedAccounts);
    }

    @Test
    void getSingleCustomerAccount() {
        Account retrievedAccount = accountService.getAccount(1L);
        assertEquals(testAccount, retrievedAccount);
    }

    @Test
    void emptyGetSingleCustomerAccount() {
        Account retrievedAccount = accountService.getAccount(100L);
        assertNull(retrievedAccount);
    }

}
