package com.cts.transaction.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "counterparties")
public class Counterparty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int counterpartyID;

    @Column(name = "name")
    private String name;

    public Counterparty() {
        super();
    }

    public Counterparty(int counterparty_ID, String name) {
        super();
        this.counterpartyID = counterparty_ID;
        this.name = name;
    }

    public int getCounterpartyID() {
        return counterpartyID;
    }

    public void setCounterpartyID(int counterpartyID) {
        this.counterpartyID = counterpartyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
