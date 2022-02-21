package com.cts.transaction.service;

import com.cts.transaction.model.AccountSummary;
import com.cts.transaction.model.TransactionStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@FeignClient(name="${microservice.account.name}", url="${microservice.account.url}")
public interface AccountMicroserviceProxy {

    @PostMapping("${url.deposit.account}")
    TransactionStatus deposit(@PathVariable Long accountId, @PathVariable double amount);

    @PostMapping("${url.withdraw.account}")
    TransactionStatus withdraw(@PathVariable Long accountId, @PathVariable double amount);

    @PostMapping("${url.transfer.account}")
    TransactionStatus transfer(@PathVariable Long sourceAccountID,@PathVariable Long destAccountID,
                               @PathVariable double amount);
    
    @GetMapping("${url.get.account}")
    AccountSummary getAccount(@PathVariable Long accountId);

    @GetMapping("${url.get.customerAccount}")
    List<AccountSummary> getCustomerAccounts(@PathVariable Long customerId);
}
