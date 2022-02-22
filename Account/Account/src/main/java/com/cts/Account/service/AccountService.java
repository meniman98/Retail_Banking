package com.cts.Account.service;

import com.cts.Account.model.Account;
import com.cts.Account.model.AccountCreationStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

public interface AccountService {
    AccountCreationStatus createAccount(@PathVariable Long customerId,
                                        @RequestBody Account account);
    Set<Account> getCustomerAccounts(@PathVariable Long customerId);
    Account getAccount(@PathVariable Long accountId);
}
