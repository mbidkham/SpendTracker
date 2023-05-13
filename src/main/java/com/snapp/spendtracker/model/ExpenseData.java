package com.snapp.spendtracker.model;

import lombok.Data;

import javax.persistence.*;
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
