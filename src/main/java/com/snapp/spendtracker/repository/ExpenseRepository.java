package com.snapp.spendtracker.repository;

import com.snapp.spendtracker.infrastructure.domain.ExpenseEntity;
import com.snapp.spendtracker.infrastructure.domain.SpendingCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    @Query("SELECT SUM(e.amount) FROM ExpenseEntity e WHERE e.category=:category AND e.createdAt BETWEEN :startDate AND :endDate")
    BigDecimal sumExpensesAmount(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                 @Param("category") SpendingCategoryEntity category);

    @Query("SELECT SUM(e.amount) AS total, e.category.name FROM ExpenseEntity e WHERE e.category.id=:category" +
        " AND e.category.user.userName=:userName" +
        " AND e.createdAt BETWEEN :startDate AND :endDate group by e.category.name")
    List<Object[]> sumExpensesAmountReporter(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                             @Param("category") Long category, @Param("userName") String userName);


}
