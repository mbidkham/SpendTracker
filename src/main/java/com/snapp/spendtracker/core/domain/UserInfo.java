package com.snapp.spendtracker.core.domain;

import com.snapp.spendtracker.infrastructure.domain.UserInformationEntity;

public interface UserInfo {
    UserInformationEntity loadUserByUserName(String userName);

}
