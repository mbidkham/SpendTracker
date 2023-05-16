package com.snapp.spendtracker.service;

import com.snapp.spendtracker.config.RequestInfo;
import com.snapp.spendtracker.controller.dto.AddCategoryDto;
import com.snapp.spendtracker.controller.dto.CategoryDto;
import com.snapp.spendtracker.controller.dto.SearchCategoryDto;
import com.snapp.spendtracker.exception.InvalidInputDataException;
import com.snapp.spendtracker.model.SpendingCategory;
import com.snapp.spendtracker.model.UserInformation;
import com.snapp.spendtracker.repository.CategoryRepository;
import com.snapp.spendtracker.util.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final RequestInfo requestInfo;
    private final UserService userService;

    public String addNewCategory(AddCategoryDto categoryDto) {
        var user = userService.loadUserByUserName(requestInfo.getUserName());
        checkDuplicateCategories(categoryDto.name(), user);
        var category = SpendingCategory
            .builder()
            .user(user)
            .limitAmount(categoryDto.limit())
            .name(categoryDto.name())
            .build();
        categoryRepository.save(category);
        return "Added Successfully.";
    }

    @Transactional(readOnly = true)
    public Page<CategoryDto> retrieveCategories(SearchCategoryDto searchCategoryDto) {
        var user = userService.loadUserByUserName(requestInfo.getUserName());
        Page<SpendingCategory> paginatedUserCategories = categoryRepository
            .findAllByNameContainingIgnoreCaseAndUser(searchCategoryDto.name(), user,
                PageRequest.of(searchCategoryDto.page(), searchCategoryDto.pageSize()));
        if (!paginatedUserCategories.isEmpty()) {
            return paginatedUserCategories
                .map(categoryMapper::map);
        }
        return Page.empty();
    }

    @Transactional(readOnly = true)
    public Page<CategoryDto> getAllUserCategories(int page, int pageSize) {
        var user = userService.loadUserByUserName(requestInfo.getUserName());
        Page<SpendingCategory> paginatedUserCategories = categoryRepository
            .findAllByUser_Id(user.getId(), PageRequest.of(page, pageSize));
        if (!paginatedUserCategories.isEmpty()) {
            return paginatedUserCategories
                .map(categoryMapper::map);
        }
        return Page.empty();
    }

    public void checkDuplicateCategories(String categoryName, UserInformation user) {
        if (!categoryRepository.findAllByNameAndUser_Id(categoryName, user.getId()).isEmpty()) {
            throw new InvalidInputDataException("This category is exist for you.Please Enter a new!");
        }
    }

}
