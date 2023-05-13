package com.snapp.spendtracker.util;

import com.snapp.spendtracker.controller.dto.AddCategoryDto;
import com.snapp.spendtracker.model.SpendingCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CategoryMapper {

    @Mapping(target = "displayName", source = "inquiryResponse", qualifiedByName = "mapNameFamilyToDisplayName")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth", qualifiedByName = "mapBirthDateToInstant")
    @Mapping(target = "nationalId", source = "nationalCode")
    SpendingCategory map(AddCategoryDto categoryDto);

}
