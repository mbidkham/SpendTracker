package com.snapp.spendtracker.util;

import com.snapp.spendtracker.infrastructure.api.dto.CategoryDto;
import com.snapp.spendtracker.infrastructure.domain.SpendingCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CategoryMapper {
    CategoryDto map(SpendingCategoryEntity categoryDto);

}
