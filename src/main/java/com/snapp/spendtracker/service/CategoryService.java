package com.snapp.spendtracker.service;

import com.snapp.spendtracker.controller.dto.AddCategoryDto;
import com.snapp.spendtracker.exception.InvalidInputDataException;
import com.snapp.spendtracker.model.SpendingCategory;
import com.snapp.spendtracker.model.UserInformation;
import com.snapp.spendtracker.repository.CategoryRepository;
import com.snapp.spendtracker.repository.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserInformationRepository userInformationRepository;

    public String addNewCategory(AddCategoryDto categoryDto) {
        // TODO: add RequestInfo class and fill it in filter class by token
        var user = userInformationRepository.findById(userId).orElseThrow();
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
    public void checkDuplicateCategories(String categoryName, UserInformation user){
        if(!categoryRepository.findAllByNameAndUser(categoryName, user).isEmpty()){
            throw new InvalidInputDataException("This category is exist for you.Please Enter a new!");
        }
    }

}
