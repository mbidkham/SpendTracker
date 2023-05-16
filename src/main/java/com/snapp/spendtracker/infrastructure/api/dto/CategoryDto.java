package com.snapp.spendtracker.infrastructure.api.dto;

import java.math.BigDecimal;



public record CategoryDto (String name, Long id, BigDecimal limitAmount){
}
