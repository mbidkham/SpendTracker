package com.snapp.spendtracker.application.service.command;


import com.snapp.spendtracker.exception.InvalidInputDataException;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AddCategoryCommand(String name, BigDecimal limit) {
    public AddCategoryCommand {
        if (name == null || name.isEmpty()) {
            throw new InvalidInputDataException("Please enter category name.");
        }
        if (limit == null || limit.compareTo(BigDecimal.valueOf(1)) < 1) {
            throw new InvalidInputDataException("PPlease Enter a valid Category limit amount.");
        }
    }
}
