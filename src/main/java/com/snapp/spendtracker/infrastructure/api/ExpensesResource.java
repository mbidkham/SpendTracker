package com.snapp.spendtracker.infrastructure.api;

import com.snapp.spendtracker.infrastructure.api.dto.AddExpenseDto;
import com.snapp.spendtracker.infrastructure.api.dto.ExpenseDto;
import com.snapp.spendtracker.infrastructure.api.dto.ExpenseReportDto;
import com.snapp.spendtracker.application.service.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpensesResource {
    private final ExpensesService expensesService;
    @PostMapping(value = "/add")
    public ResponseEntity<String> addExpenses(@RequestBody AddExpenseDto addExpensesDto){
        return ResponseEntity.ok(expensesService.addExpense(addExpensesDto));
    }

    @PostMapping("/report")
    public ResponseEntity<ExpenseDto> getReport(@RequestBody ExpenseReportDto expenseReportDto){
        return ResponseEntity.ok(expensesService.expenseReporter(expenseReportDto));
    }
}
