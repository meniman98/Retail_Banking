package com.cts.portal.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class Operation {
	@NotNull
	@Pattern(regexp = "(Deposit|Withdraw)", message = "Should be 'Deposit' or 'Withdraw' only...")
	private String name;
	@NotNull
	@Min(value = 10, message="Minimum value is 10.0")
	private double amount;
	@NotNull
	private Long accountId;
}
