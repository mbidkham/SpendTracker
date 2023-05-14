package com.snapp.spendtracker.controller.dto;

import java.math.BigDecimal;

public record AddExpensesDto(Long id, BigDecimal amount) {
}
