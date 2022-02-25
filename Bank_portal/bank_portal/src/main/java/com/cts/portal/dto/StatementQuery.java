package com.cts.portal.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class StatementQuery {
	@NotNull
	private Long accountId;
	private String startDate;
	private String endDate;
}
