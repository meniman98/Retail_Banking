package com.cts.Account;

public class Utils {

//    URL endpoints
    public static final String CREATE_ACCOUNT = "/createAccount/{customerId}";
    public static final String GET_CUSTOMER_ACCOUNTS = "/getCustomerAccounts/{customerId}";
    public static final String GET_ACCOUNT = "/getAccount/{accountId}";
    public static final String GET_ACCOUNT_STATEMENT = "/getAccountStatement/{accountId}";

//    Exception messages
    public static final String ACCOUNT_NOT_FOUND = "Account ID not found";
    public static final String CUSTOMER_NOT_FOUND = "Customer ID not found";
}
