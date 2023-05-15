package com.snapp.spendtracker.bdd.util;

import com.snapp.spendtracker.model.ExpenseData;
import com.snapp.spendtracker.model.SpendingCategory;
import com.snapp.spendtracker.repository.CategoryRepository;
import com.snapp.spendtracker.repository.ExpenseRepository;
import com.snapp.spendtracker.repository.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class InitialDataUtil {
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final UserInformationRepository userInformationRepository;
    @Transactional
    public SpendingCategory saveCategory(String categoryName, BigDecimal limit){
        var user = userInformationRepository.findByUserName("m.bidkham").get();
        return categoryRepository.save(SpendingCategory.builder().id(1L)
            .name(categoryName)
            .user(user)
            .limitAmount(limit)
            .build());

    }
    public void saveNewExpense(SpendingCategory spendingCategory, BigDecimal amount) {
        expenseRepository.save(ExpenseData.builder()
            .createdAt(LocalDate.now())
            .amount(BigDecimal.valueOf(300_000))
            .category(spendingCategory)
            .build());
    }
}
