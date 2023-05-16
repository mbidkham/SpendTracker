package com.snapp.spendtracker.application.service.query;

import com.snapp.spendtracker.exception.InvalidInputDataException;

import java.time.LocalDate;

public record ExpenseReportQuery(LocalDate from, LocalDate to, Long categoryId) {
    public ExpenseReportQuery {
        if (from == null) {
            //TODO: validate date input pattern
            throw new InvalidInputDataException("Please enter the start date.");
        }
        if (to == null) {
            throw new InvalidInputDataException("Please enter the end date.");
        }
    }
}
