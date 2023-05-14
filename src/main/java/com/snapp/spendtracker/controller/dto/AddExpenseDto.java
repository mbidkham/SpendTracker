package com.snapp.spendtracker.controller.dto;

import java.math.BigDecimal;

public record AddExpenseDto(Long id, BigDecimal amount) {
}
