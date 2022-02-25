package com.cts.Account;

import com.cts.Account.model.Account;
import com.cts.Account.repo.AccountRepo;
import com.cts.Account.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    AccountServiceImpl accountService;

    @Mock
    AccountRepo accountRepo;


    Account testAccount = new Account(1, null);
    Account testAccount2 = new Account(2, null);
    List<Account> customerAccounts = new ArrayList<>();

    /*1. create an account
    * 2. get back that same account with the ID
    * 3. Make sure that is the same account*/

    @BeforeEach
    void setUp() {
        customerAccounts.add(testAccount);
        customerAccounts.add(testAccount2);
        Mockito.when(accountRepo.save(testAccount)).thenReturn(testAccount);
        Mockito.when(accountRepo.getById(testAccount.getAccountId())).thenReturn(testAccount);
        Mockito.when(accountRepo.findByCustomerId(0L)).thenReturn(customerAccounts);

    }

    @Test
    void createAccount() {
        accountService.createAccount(testAccount.getCustomerId());
        assertEquals(accountRepo.getById(testAccount.getAccountId()), testAccount);
    }

    @Test
    void failureCreateAccount() {
        accountService.createAccount(testAccount2.getCustomerId());
        assertNotEquals(accountRepo.getById(testAccount.getAccountId()), testAccount2);
    }

    @Test
    void getCustomerAccounts() {
        List<Account> returnedAccounts = accountService.getCustomerAccounts(0L);
        assertEquals(returnedAccounts, customerAccounts);
    }

}
