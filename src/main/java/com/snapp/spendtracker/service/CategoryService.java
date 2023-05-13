package com.snapp.spendtracker.service;

import com.snapp.spendtracker.config.RequestInfo;
import com.snapp.spendtracker.controller.dto.AddCategoryDto;
import com.snapp.spendtracker.controller.dto.CategoryDto;
import com.snapp.spendtracker.exception.InvalidInputDataException;
import com.snapp.spendtracker.model.SpendingCategory;
import com.snapp.spendtracker.model.UserInformation;
import com.snapp.spendtracker.repository.CategoryRepository;
import com.snapp.spendtracker.repository.UserInformationRepository;
import com.snapp.spendtracker.util.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserInformationRepository userInformationRepository;
    private final CategoryMapper categoryMapper;
    private final RequestInfo requestInfo;

    public String addNewCategory(AddCategoryDto categoryDto) {
        var user = userInformationRepository.findByUserName(requestInfo.getUserName()).orElseThrow();
        checkDuplicateCategories(categoryDto.name(), user);
        var category = SpendingCategory
            .builder()
            .user(user)
            .limit(categoryDto.limit())
            .name(categoryDto.name())
            .build();
        categoryRepository.save(category);
        return "Added Successfully.";
    }

    public List<CategoryDto> retrieveCategories(UserInformation user) {
        List<SpendingCategory> userCategories = categoryRepository.findAllByUser(user);
        if (!userCategories.isEmpty()){
            return userCategories.stream().map(categoryMapper::map).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
    public void checkDuplicateCategories(String categoryName, UserInformation user){
        if(!categoryRepository.findAllByNameAndUser(categoryName, user).isEmpty()){
            throw new InvalidInputDataException("This category is exist for you.Please Enter a new!");
        }
    }

}
