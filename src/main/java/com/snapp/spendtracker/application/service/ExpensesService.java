package com.snapp.spendtracker.application.service;

import com.snapp.spendtracker.config.RequestInfo;
import com.snapp.spendtracker.infrastructure.api.dto.AddExpenseDto;
import com.snapp.spendtracker.infrastructure.api.dto.ExpenseDto;
import com.snapp.spendtracker.infrastructure.api.dto.ExpenseReportDto;
import com.snapp.spendtracker.exception.InvalidInputDataException;
import com.snapp.spendtracker.infrastructure.domain.SpendingCategoryEntity;
import com.snapp.spendtracker.infrastructure.repository.UserRepository;
import com.snapp.spendtracker.infrastructure.domain.ExpenseEntity;
import com.snapp.spendtracker.infrastructure.repository.CategoryRepository;
import com.snapp.spendtracker.infrastructure.repository.ExpenseRepository;
import com.snapp.spendtracker.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpensesService {
    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;
    private final RequestInfo requestInfo;
    private final UserRepository userService;

    public String addExpense(AddExpenseDto addExpenseDto) {
        var user = userService.loadUserByUserName(requestInfo.getUserName());
        var category = categoryRepository.findByIdAndUser(addExpenseDto.id(), user)
            .orElseThrow(() -> new InvalidInputDataException("This category is not exist for you."));
        var expense = ExpenseEntity.builder()
            .amount(addExpenseDto.amount())
            .category(category)
            .createdAt(LocalDate.now())
            .build();
        expenseRepository.save(expense);
        return checkExpenseLimit(category, addExpenseDto.amount());
    }

    @Transactional(readOnly = true)
    public ExpenseDto expenseReporter(ExpenseReportDto reportDto) {
        List<Object[]> reportResult = expenseRepository.sumExpensesAmountReporter(reportDto.from(), reportDto.to(),
            reportDto.categoryId(), requestInfo.getUserName());
        if(reportResult.isEmpty()){
            return ExpenseDto.builder().build();
        }
        var amount = (BigDecimal) reportResult.get(0)[0];
        var name = (String) reportResult.get(0)[1];
        return ExpenseDto.builder()
            .totalAmount(amount)
            .name(name)
            .build();
    }

    private String checkExpenseLimit(SpendingCategoryEntity category, BigDecimal newAmount) {
        var monthlyCategoryExpenses =
            expenseRepository.sumExpensesAmount(DateUtils.getFirstDayOfCurrentMonth(), LocalDate.now(), category);
        if (monthlyCategoryExpenses.add(newAmount).compareTo(category.getLimitAmount()) > 0) {
            return "NOTICE!!You are spending too money on buying " + category.getName() + " in this month!!";
        }
        return "WELL DONE!";
    }

}
