package com.cts.Account.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long statementId;
    private String refNo;
    private Account accountId;
    private LocalDate date;
    private double withdrawal;
    private LocalDate valueDate;
    private String narration;
}
