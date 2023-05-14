package com.snapp.spendtracker.controller.dto;

import java.time.LocalDate;

public record ExpenseReportDto(LocalDate from, LocalDate to, Long categoryId) {
}
