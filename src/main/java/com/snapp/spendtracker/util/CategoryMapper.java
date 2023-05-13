package com.snapp.spendtracker.util;

import com.snapp.spendtracker.controller.dto.CategoryDto;
import com.snapp.spendtracker.model.SpendingCategory;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CategoryMapper {
    CategoryDto map(SpendingCategory categoryDto);

}
