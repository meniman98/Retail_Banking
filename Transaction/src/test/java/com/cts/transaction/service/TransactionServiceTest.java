package com.cts.transaction.service;

import com.cts.transaction.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransactionServiceTest {

    @Mock
    private AccountMicroserviceProxy accountMicroserviceProxy;

    @Mock
    private CustomerMicroserviceProxy customerMicroserviceProxy;

    @Mock
    private RuleMicroserviceProxy ruleMicroserviceProxy;

    private final Long CUSTOMER_ID = 1L;

    private CustomerSummary customer = new CustomerSummary(CUSTOMER_ID, "Kim", "Test",
            "test@test.com", "0123456789", "1, Test France", "01/01/1998");

    private Counterparty validCounterparty = new Counterparty(1, "Solofo Test");

    private Transaction validTransaction = new Transaction(1, 1L, 1, validCounterparty,
            LocalDate.now(), "deposit", "completed", "+1000.0");

    private List<AccountSummary> accountsSummary = new ArrayList<>();

    @InjectMocks
    private TransactionServiceImp transactionServiceImp;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.accountsSummary.add(new AccountSummary("12345", "Savings", "01/01/2022",
                1L, 500.0, CUSTOMER_ID));
        this.accountsSummary.add(new AccountSummary("54321", "Current", "01/01/2022",
                2L, 500.0, CUSTOMER_ID));
    }

    @Test
    public void testValidDeposit(){
        TransactionStatus transactionStatus = transactionServiceImp.deposit(1L, 100.0);
        assertNotNull(transactionStatus);
    }
}
