package com.snapp.spendtracker.infrastructure.api.dto;


import com.snapp.spendtracker.exception.InvalidInputDataException;
import lombok.Builder;

@Builder
public record SearchCategoryDto(String name, int page, int pageSize) {

    public SearchCategoryDto {
        if (name == null || name.isEmpty()) {
            throw new InvalidInputDataException("Please Enter category name.");
        }
    }
}
