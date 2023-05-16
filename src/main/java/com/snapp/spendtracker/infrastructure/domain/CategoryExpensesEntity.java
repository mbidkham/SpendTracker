package com.snapp.spendtracker.infrastructure.domain;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class CategoryExpensesEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private String categoryName;
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY", referencedColumnName = "id")
    @ToString.Exclude
    private SpendingCategoryEntity category;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXPENSES", referencedColumnName = "id")
    @ToString.Exclude
    private List<ExpenseEntity> expenseData;

}
