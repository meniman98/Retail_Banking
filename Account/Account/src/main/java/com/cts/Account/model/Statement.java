package com.cts.Account.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short statementId;
    private String refNo;
    private Account accountId;
    private LocalDate date;
    private double withdrawal;
    private LocalDate valueDate;
//    TODO: add narration
}
