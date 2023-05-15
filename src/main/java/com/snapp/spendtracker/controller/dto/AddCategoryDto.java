package com.snapp.spendtracker.controller.dto;


import com.snapp.spendtracker.exception.InvalidInputDataException;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AddCategoryDto(String name, BigDecimal limit) {
    public AddCategoryDto {
        if (name == null || name.isEmpty()) {
            throw new InvalidInputDataException("Please enter category name.");
        }
        if (limit == null || limit.compareTo(BigDecimal.valueOf(1)) < 1) {
            throw new InvalidInputDataException("PPlease Enter a valid Category limit amount.");
        }
    }
}
