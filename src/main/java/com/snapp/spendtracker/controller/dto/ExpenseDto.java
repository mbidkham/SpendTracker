package com.snapp.spendtracker.controller.dto;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record ExpenseDto(String name, BigDecimal totalAmount) {
}
