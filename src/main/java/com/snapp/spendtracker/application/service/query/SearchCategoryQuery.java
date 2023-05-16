package com.snapp.spendtracker.application.service.query;


import com.snapp.spendtracker.exception.InvalidInputDataException;
import lombok.Builder;

@Builder
public record SearchCategoryQuery(String name, int page, int pageSize) {

    public SearchCategoryQuery {
        if (name == null || name.isEmpty()) {
            throw new InvalidInputDataException("Please Enter category name.");
        }
    }
}
