package com.cts.Account;

import com.cts.Account.model.Statement;
import com.cts.Account.repo.StatementRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class AccountApplicationTests {

	@Autowired
	StatementRepo statementRepo;

	@Test
	void contextLoads() {
	}



	@Test
	void getListOfDates() {
		List<Statement> statements = statementRepo.findAllByDate(1L,LocalDate.of(2000, 1, 1),
				LocalDate.of(2000, 12, 30));
	}

	@Test
	void getDate() {
		Statement statement = statementRepo.findByDate(1L);
	}

}
