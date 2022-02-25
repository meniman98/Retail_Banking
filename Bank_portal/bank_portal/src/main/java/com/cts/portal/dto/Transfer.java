package com.cts.portal.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Transfer {
	@NotNull
	private Long sourceAccountId;
	@NotNull
	@Size(min = 12, max = 12)
	private String targetAccountNo;
	@NotNull
	@Min(value = 5, message="Amount minimum is 5...")
	private double amount;
}
