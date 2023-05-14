package com.snapp.spendtracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name = "EXPENSES")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(optional = false)
    private SpendingCategory category;

    private BigDecimal amount;
    private LocalDate createdAt;


}
