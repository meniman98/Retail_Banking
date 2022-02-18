package com.cts.Account.service;

import com.cts.Account.model.TransactionStatus;
import org.springframework.web.bind.annotation.PathVariable;

public interface TransactionService {
    TransactionStatus deposit(@PathVariable Long accountId,  @PathVariable double amount);
    TransactionStatus withdraw(@PathVariable Long accountId, @PathVariable double amount);
}
