package com.cts.Account.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;
    private String name;
    private LocalDate localDate;
    private String address;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Account> accountSet;
}
