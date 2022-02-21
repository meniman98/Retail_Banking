package com.cts.transaction.model;

import lombok.Data;

@Data
public class TransactionStatus {
    private long id;
    private long accountId;
    private String message;
    private double sourceBalance;
    private double destinationBalance;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getSourceBalance() {
        return sourceBalance;
    }

    public void setSourceBalance(double sourceBalance) {
        this.sourceBalance = sourceBalance;
    }

    public double getDestinationBalance() {
        return destinationBalance;
    }

    public void setDestinationBalance(double destinationBalance) {
        this.destinationBalance = destinationBalance;
    }
}
