package com.snapp.spendtracker.core.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCategory extends DomainAggregate{
    private String userId;
    private String categoryName;
    private UserInfoDomain user;
    private CategoryDomain category;

}
