package com.snapp.spendtracker.application.service.command;

import com.snapp.spendtracker.exception.InvalidInputDataException;
import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record AddExpenseCommand(Long id, BigDecimal amount) {

    public AddExpenseCommand {
        if (id == null) {
            throw new InvalidInputDataException("Please Enter Category id!");
        }
        if (amount == null || amount.compareTo(BigDecimal.valueOf(1)) < 1) {
            throw new InvalidInputDataException("Please enter a valid amount.");
        }
    }
}
