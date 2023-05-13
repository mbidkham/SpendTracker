package com.snapp.spendtracker.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "EXPENSES")
public class ExpenseData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(optional = false)
    private SpendingCategory category;

    private BigDecimal amount;
    private Date createdAt;


}
