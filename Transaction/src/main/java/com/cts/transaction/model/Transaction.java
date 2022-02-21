package com.cts.transaction.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int transactionID;

    @Column(name = "accountID")
    private Long accountID;

    @Column(name = "statementID")
    private int statementID;

    @OneToOne
    @JoinColumn(name = "counterpartyID", referencedColumnName = "id")
    private Counterparty counterParty;

    @Column(name = "transactionDate", columnDefinition = "DATE")
    private LocalDate transactionDate;

    @Column(name = "transactionType")
    private String transactionType;

    @Column(name = "transactionStatus")
    private String transactionStatus;

    @Column(name = "amount")
    private String amount;

    public Transaction() {
        super();
    }

    public Transaction(int transactionID, Long accountID, int statementID, Counterparty counterParty,
                       LocalDate transactionDate, String transactionType, String transactionStatus, String amount) {
        this.transactionID = transactionID;
        this.accountID = accountID;
        this.statementID = statementID;
        this.counterParty = counterParty;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.transactionStatus = transactionStatus;
        this.amount = amount;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
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

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
