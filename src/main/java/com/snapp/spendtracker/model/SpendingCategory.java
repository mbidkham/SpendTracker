package com.snapp.spendtracker.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class SpendingCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private UserInformation user;

    private String name;
    private BigDecimal amount;
    private BigDecimal limit;


}
