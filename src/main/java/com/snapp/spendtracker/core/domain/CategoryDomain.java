package com.snapp.spendtracker.core.domain;

import com.snapp.spendtracker.infrastructure.domain.UserInformationEntity;

import java.math.BigDecimal;

public record CategoryDomain(UserInformationEntity user, String name, BigDecimal limitAmount) {

}
