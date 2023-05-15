package com.snapp.spendtracker.controller.dto;

import com.snapp.spendtracker.exception.InvalidInputDataException;

import java.time.LocalDate;

public record ExpenseReportDto(LocalDate from, LocalDate to, Long categoryId) {
    public ExpenseReportDto {
        if (from == null) {
            //TODO: validate date input pattern
            throw new InvalidInputDataException("Please enter the start date.");
        }
        if (to == null) {
            throw new InvalidInputDataException("Please enter the end date.");
        }
    }
}
