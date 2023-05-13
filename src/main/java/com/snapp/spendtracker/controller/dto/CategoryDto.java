package com.snapp.spendtracker.controller.dto;

import java.math.BigDecimal;

public record CategoryDto (String name, Long id, BigDecimal limit){
}
