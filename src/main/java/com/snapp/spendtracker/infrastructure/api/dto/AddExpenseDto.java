package com.snapp.spendtracker.infrastructure.api.dto;

import com.snapp.spendtracker.exception.InvalidInputDataException;
import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record AddExpenseDto(Long id, BigDecimal amount) {

    public AddExpenseDto {
        if (id == null) {
            throw new InvalidInputDataException("Please Enter Category id!");
        }
        if (amount == null || amount.compareTo(BigDecimal.valueOf(1)) < 1) {
            throw new InvalidInputDataException("Please enter a valid amount.");
        }
    }
}
