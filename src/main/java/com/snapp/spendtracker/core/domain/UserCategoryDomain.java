package com.snapp.spendtracker.core.domain;

import com.snapp.spendtracker.infrastructure.api.dto.CategoryDto;
import com.snapp.spendtracker.infrastructure.domain.UserInformationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserCategoryDomain {

    Page<CategoryDto> findAllByNameContainingIgnoreCaseAndUser(String name, UserInformationEntity userInformation,
                                                               Pageable pageable);


}
