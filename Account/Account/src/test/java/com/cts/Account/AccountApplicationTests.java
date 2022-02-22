package com.cts.Account;

import com.cts.Account.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AccountApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testForNull() {
		assertThrows(NullPointerException.class, () -> {
			Account account =  new Account();
			account.getAccountNo();
		});
	}

}
