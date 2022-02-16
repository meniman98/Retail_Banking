package com.cts.transaction.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int transactionID;

    @Column(name = "accountID")
    private int accountID;

    @Column(name = "statementID")
    private int statementID;

    @OneToOne
    @JoinColumn(name = "counterpartyID", referencedColumnName = "id")
    private Counterparty counterParty;

    @Column(name = "transactionDate")
    private Date transactionDate;

    @Column(name = "transactionType")
    private String transactionType;

    @Column(name = "amount")
    private double amount;

    public Transaction() {
    }

    public Transaction(int transactionID, int accountID, int statementID, Counterparty counterParty,
                       Date transactionDate, String transactionType, double amount) {
        this.transactionID = transactionID;
        this.accountID = accountID;
        this.statementID = statementID;
        this.counterParty = counterParty;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getStatementID() {
        return statementID;
    }

    public void setStatementID(int statementID) {
        this.statementID = statementID;
    }

    public Counterparty getCounterParty() {
        return counterParty;
    }

    public void setCounterParty(Counterparty counterParty) {
        this.counterParty = counterParty;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
