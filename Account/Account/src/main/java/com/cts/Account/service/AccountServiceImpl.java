package com.cts.Account.service;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cts.Account.Utils;
import com.cts.Account.model.Account;
import com.cts.Account.model.AccountCreationStatus;
import com.cts.Account.repo.AccountRepo;


@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepo accountRepo;

	@Override
	public AccountCreationStatus createAccount(Long customerId) {
//            Make a new account, and pass in the customer ID
//            as a parameter
		Account baseAccount = new Account();
		baseAccount.setCustomerId(customerId);
		Account savingsAccount = createAccount(baseAccount, "Savings");
		Account currentAccount = createAccount(baseAccount, "Current");
		// Add saving account
		accountRepo.save(savingsAccount);
//            Add a current account
		accountRepo.save(currentAccount);
		return new AccountCreationStatus(baseAccount, "Account successfully created");
	}

	@Override
	public List<Account> getCustomerAccounts(Long customerId) {
		List<Account> customerAccounts = this.accountRepo.findByCustomerId(customerId);
		if (!customerAccounts.isEmpty()) {
			return customerAccounts;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, Utils.ACCOUNT_NOT_FOUND);
		}
	}

	@Override
	public Account getAccount(Long accountId) {
		if (accountRepo.existsById(accountId)) {
			return accountRepo.getById(accountId);
		} else if (!accountRepo.existsById(accountId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, Utils.ACCOUNT_NOT_FOUND);
		} else {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Account getAccountByNo(String accountNo) {
		if (accountRepo.findByAccountNo(accountNo) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, Utils.ACCOUNT_NUMBER_NOT_FOUND);
		}
		else {
			return accountRepo.findByAccountNo(accountNo);
		}
	}

	private Account createAccount(Account baseAccount, String accountType) {
		Account newAccount = new Account(accountType, baseAccount.getAccountNo(), baseAccount.getBalance(),
				baseAccount.getCustomerId(), LocalDate.now());
		// generate Random digits of size 12 as account No
		newAccount.setAccountNo(RandomStringUtils.random(12, false, true));
		return newAccount;
	}

}
