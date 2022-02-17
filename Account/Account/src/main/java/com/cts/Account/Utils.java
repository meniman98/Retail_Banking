package com.cts.Account;

public class Utils {

//    URL endpoints
    public static final String CREATE_ACCOUNT = "/createAccount{id}";
    public static final String GET_CUSTOMER_ACCOUNTS = "/getCustomerAccounts{id}";
    public static final String GET_ACCOUNT = "/getAccount{id}";
    public static final String GET_ACCOUNT_STATEMENT = "/getAccountStatement{id}";

//    Exception messages
    public static final String ACCOUNT_NOT_FOUND = "Account ID not found";
    public static final String CUSTOMER_NOT_FOUND = "Customer ID not found";
}
