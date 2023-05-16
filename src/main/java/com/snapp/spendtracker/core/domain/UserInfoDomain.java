package com.snapp.spendtracker.core.domain;

import com.snapp.spendtracker.infrastructure.domain.UserRole;


public record UserInfoDomain (String userName, String password, UserRole userRole){
}
