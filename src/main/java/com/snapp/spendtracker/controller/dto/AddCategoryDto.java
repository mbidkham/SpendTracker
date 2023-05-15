package com.snapp.spendtracker.controller.dto;


import com.snapp.spendtracker.exception.InvalidInputDataException;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record AddCategoryDto(@NotNull(message = "Please enter category name.") String name,
                             @NotNull(message = "Please Enter a valid Category limit amount.")
                             @Min(value = 1) BigDecimal limit) {
    public AddCategoryDto {
        if (name == null || name.isEmpty()) {
            throw new InvalidInputDataException("Please enter category name.");
        }
        if (limit == null || limit.compareTo(BigDecimal.valueOf(1)) < 1) {
            throw new InvalidInputDataException("PPlease Enter a valid Category limit amount.");
        }
    }
}
