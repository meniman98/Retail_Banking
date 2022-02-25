package com.cts.portal.dto;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
public class Statement {
    private long statementId;
    private String refNo;
    private long accountId;
    private LocalDate date;
    private double withdrawal;
    private double deposit;
    private LocalDate valueDate;
    private String narration;
    private double balance;
}
