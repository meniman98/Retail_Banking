package com.cts.Account.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;
    private String accountNo;
    private LocalDate dateOfCreation;
    private String accountType;
    private double balance;
    private long customerId;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Statement> statementSet;

    /*Creating a constructor for customerId only
    * in order to ensure that a value is passed in*/
    public Account(long customer) {
        this.customerId = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId == account.accountId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }
}
