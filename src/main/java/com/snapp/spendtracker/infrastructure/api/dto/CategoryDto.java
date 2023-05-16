package com.snapp.spendtracker.infrastructure.api.dto;

import com.snapp.spendtracker.core.domain.DomainAggregate;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder()
@RequiredArgsConstructor
@Log4j2
@ToString
public class CategoryDto extends DomainAggregate {
    private String name;
    private Long id;
    private BigDecimal limitAmount;
}
