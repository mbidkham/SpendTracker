package com.snapp.spendtracker.controller;

import com.snapp.spendtracker.controller.dto.AddExpenseDto;
import com.snapp.spendtracker.controller.dto.ExpenseDto;
import com.snapp.spendtracker.controller.dto.ExpenseReportDto;
import com.snapp.spendtracker.service.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpensesController {
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
