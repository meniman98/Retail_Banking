package com.cts.transaction.service;

import com.cts.transaction.model.*;
import com.cts.transaction.repo.TransactionRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

public class TransactionServiceTest {

    @Mock
    private AccountMicroserviceProxy accountMicroserviceProxy;

    @Mock
    private CustomerMicroserviceProxy customerMicroserviceProxy;

    @Mock
    private RuleMicroserviceProxy ruleMicroserviceProxy;

    @Mock
    private TransactionRepo transactionRepo;

    private final Long CUSTOMER_ID = 1L;

    private final String ACCEPTED_STATUS = "allowed";

    private final String REJECTED_STATUS = "denied";

    private CustomerSummary customer = new CustomerSummary(CUSTOMER_ID, "Kim", "Test",
            "test@test.com", "0123456789", "1, Test France", "01/01/1998");

    private Counterparty validCounterparty = new Counterparty(1, "Solofo Test");

    private Transaction validTransaction = new Transaction(3, 1L, 1, validCounterparty,
            LocalDate.now(), "deposit", "completed", "+1000.0");

    private TransactionStatus validTransactionStatus = new TransactionStatus();

    private RuleStatus ruleStatusOK = new RuleStatus(ACCEPTED_STATUS);

    private RuleStatus rulesStatusNotOK = new RuleStatus(REJECTED_STATUS);

    private List<AccountSummary> accountsSummary = new ArrayList<>();

    private List<Transaction> transactionList = new ArrayList<>();

    @InjectMocks
    private TransactionServiceImp transactionServiceImp;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.accountsSummary.add(new AccountSummary("12345", "Savings", "01/01/2022",
                1L, 500.0, CUSTOMER_ID));
        this.accountsSummary.add(new AccountSummary("54321", "Current", "01/01/2022",
                2L, 500.0, CUSTOMER_ID));

        this.transactionList.add(validTransaction);

        Mockito.when(this.transactionRepo.save(this.validTransaction)).thenReturn(this.validTransaction);

        Mockito.when(this.accountMicroserviceProxy.getCustomerAccounts(this.customer.getCustomerId()))
             .thenReturn(this.accountsSummary);

    }

    @Test
    public void testValidDeposit() {
        Mockito.when(this.accountMicroserviceProxy.getAccount(1L)).thenReturn(this.accountsSummary.get(0));
        Mockito.when(this.customerMicroserviceProxy.getCustomerDetails(1L, "")).thenReturn(this.customer);
        Mockito.when(this.accountMicroserviceProxy.deposit(1L, 100.0)).
                thenReturn(this.validTransactionStatus);
        TransactionStatus transactionStatus = transactionServiceImp.deposit(1L, 100.0, "");
        assertEquals("completed", transactionStatus.getMessage());
    }

    @Test
    public void testCancelDeposit() {
        //Fail connection with Account Microservice
        TransactionStatus transactionStatus = transactionServiceImp.deposit(1L, 100.0, "");
        assertEquals("canceled", transactionStatus.getMessage());
    }

    @Test
    public void testValidWithdraw() {
        double amountWithdraw = 100.0;
        double balanceAfterWithdraw;
        Mockito.when(this.accountMicroserviceProxy.getAccount(1L)).thenReturn(this.accountsSummary.get(0));
        Mockito.when(this.customerMicroserviceProxy.getCustomerDetails(1L, "")).thenReturn(this.customer);
        Mockito.when(this.accountMicroserviceProxy.withdraw(1L, amountWithdraw)).
                thenReturn(this.validTransactionStatus);
        balanceAfterWithdraw = this.accountsSummary.get(0).getBalance() - amountWithdraw;
        Mockito.when(this.ruleMicroserviceProxy.evaluateMinBal(balanceAfterWithdraw,
                this.accountsSummary.get(0).getAccountType())).thenReturn(this.ruleStatusOK);
        TransactionStatus transactionStatus = transactionServiceImp.withdraw(1L, amountWithdraw, "");
        assertEquals("completed", transactionStatus.getMessage());
    }

    @Test
    public void testDeniedWithdraw(){
        double amountWithdraw = 1000.0;
        double balanceAfterWithdraw;
        Mockito.when(this.accountMicroserviceProxy.getAccount(1L)).thenReturn(this.accountsSummary.get(0));
        Mockito.when(this.customerMicroserviceProxy.getCustomerDetails(1L, "")).thenReturn(this.customer);
        Mockito.when(this.accountMicroserviceProxy.withdraw(1L, amountWithdraw)).
                thenReturn(this.validTransactionStatus);
        balanceAfterWithdraw = this.accountsSummary.get(0).getBalance() - amountWithdraw;
        Mockito.when(this.ruleMicroserviceProxy.evaluateMinBal(balanceAfterWithdraw,
                this.accountsSummary.get(0).getAccountType())).thenReturn(this.rulesStatusNotOK);
        TransactionStatus transactionStatus = transactionServiceImp.withdraw(1L, amountWithdraw, "");
        assertEquals("declined", transactionStatus.getMessage());
    }

    @Test
    public void testValidTransfer(){
        double amountWithdraw = 100.0;
        double balanceAfterWithdraw;
        Mockito.when(this.accountMicroserviceProxy.getAccount(1L)).thenReturn(this.accountsSummary.get(0));
        Mockito.when(this.accountMicroserviceProxy.getAccount(2L)).thenReturn(this.accountsSummary.get(1));
        Mockito.when(this.customerMicroserviceProxy.getCustomerDetails(1L, "")).thenReturn(this.customer);
        Mockito.when(this.accountMicroserviceProxy.withdraw(1L, amountWithdraw)).
                thenReturn(this.validTransactionStatus);
        balanceAfterWithdraw = this.accountsSummary.get(0).getBalance() - amountWithdraw;
        Mockito.when(this.ruleMicroserviceProxy.evaluateMinBal(balanceAfterWithdraw,
                this.accountsSummary.get(0).getAccountType())).thenReturn(this.ruleStatusOK);
        TransactionStatus transactionStatus = transactionServiceImp.transfer(1L, 2L,
                amountWithdraw, "");
        assertEquals("completed", transactionStatus.getMessage());
    }

    @Test
    public void testDeniedTransfer(){
        double amountWithdraw = 1000.0;
        double balanceAfterWithdraw;
        Mockito.when(this.accountMicroserviceProxy.getAccount(1L)).thenReturn(this.accountsSummary.get(0));
        Mockito.when(this.accountMicroserviceProxy.getAccount(2L)).thenReturn(this.accountsSummary.get(1));
        Mockito.when(this.customerMicroserviceProxy.getCustomerDetails(1L, "")).thenReturn(this.customer);
        Mockito.when(this.accountMicroserviceProxy.withdraw(1L, amountWithdraw)).
                thenReturn(this.validTransactionStatus);
        balanceAfterWithdraw = this.accountsSummary.get(0).getBalance() - amountWithdraw;
        Mockito.when(this.ruleMicroserviceProxy.evaluateMinBal(balanceAfterWithdraw,
                this.accountsSummary.get(0).getAccountType())).thenReturn(this.rulesStatusNotOK);
        TransactionStatus transactionStatus = transactionServiceImp.transfer(1L, 2L,
                amountWithdraw, "");
        assertEquals("declined", transactionStatus.getMessage());
    }

    @Test
    public void testGetTransactionSameID(){
        Mockito.when(this.transactionRepo.findByAccountID(1L)).thenReturn(this.transactionList);
        List<Transaction> transactions = transactionServiceImp.getTransaction(this.CUSTOMER_ID);
        assertEquals(3, transactions.get(0).getTransactionID());
    }

    @Test
    public void testGetTransactionEmpty(){
        Mockito.when(this.transactionRepo.findByAccountID(1L)).thenReturn(new ArrayList<>());
        List<Transaction> transactions = transactionServiceImp.getTransaction(this.CUSTOMER_ID);
        assertEquals(0, transactions.size());

    }
}
