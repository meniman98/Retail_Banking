package com.cts.transaction.service;

import com.cts.transaction.model.TransactionStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name="account-microservice", url="localhost:8081/test")
public interface AccountMicroserviceProxy {

    @PostMapping("/deposit")
    public TransactionStatus deposit(Long accountID, double amount);

    @PostMapping("/withdraw")
    public TransactionStatus withdraw(Long accountID, double amount);
}
