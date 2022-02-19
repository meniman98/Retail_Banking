package com.cts.transaction.service;

import com.cts.transaction.model.AccountSummary;
import com.cts.transaction.model.TransactionStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="${microservice.account.name}", url="${microservice.account.url}")
public interface AccountMicroserviceProxy {

    @PostMapping("${url.deposit.account}")
    public TransactionStatus deposit(@PathVariable Long accountId, @PathVariable double amount);

    @PostMapping("${url.withdraw.account}")
    public TransactionStatus withdraw(@PathVariable Long accountId, @PathVariable double amount);
    
    @GetMapping("${url.get.account}")
    public AccountSummary getAccount(@PathVariable Long accountId);
}
