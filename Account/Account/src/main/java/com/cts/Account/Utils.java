package com.cts.Account;

public class Utils {

//    URL endpoints
    public static final String CREATE_ACCOUNT = "/createAccount/{customerId}";
    public static final String GET_CUSTOMER_ACCOUNTS = "/getCustomerAccounts/{customerId}";
    public static final String GET_ACCOUNT = "/getAccount/{accountId}";
    public static final String GET_ACCOUNT_BY_NUMBER = "/getAccountNo/{accountNo}";
    public static final String GET_STATEMENT_LIST = "/getAccountStatement/{accountId}/{startDate}/{endDate}";
    public static final String GET_SINGLE_STATEMENT = "/getAccountStatement/{accountId}";
    public static final String DEPOSIT = "/deposit/{accountId}/{amount}";
    public static final String WITHDRAW = "withdraw/{accountId}/{amount}";

//    Exception messages
    public static final String ACCOUNT_NOT_FOUND = "Account ID not found";
    public static final String STATEMENT_NOT_FOUND = "This Account ID does exist, but does not belong to any statement";
    public static final String ACCOUNT_NUMBER_NOT_FOUND = "This Account number does not exist";
}
