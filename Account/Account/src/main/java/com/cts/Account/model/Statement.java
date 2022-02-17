package com.cts.Account.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "Statement")
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long statementId;
    private String refNo;
    private long accountId;
    private LocalDate date;
    private double withdrawal;
    private LocalDate valueDate;
    private String narration;
}
