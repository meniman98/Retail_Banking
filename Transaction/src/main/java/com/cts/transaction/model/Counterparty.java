package com.cts.transaction.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "counterparties")
public class Counterparty {

    private int counterparty_ID;

    private String name;
}
