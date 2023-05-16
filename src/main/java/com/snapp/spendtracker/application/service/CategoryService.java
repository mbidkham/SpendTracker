package com.snapp.spendtracker.application.service;

import com.snapp.spendtracker.application.service.command.AddCategoryCommand;
import com.snapp.spendtracker.application.service.query.SearchCategoryQuery;
import com.snapp.spendtracker.config.RequestInfo;
import com.snapp.spendtracker.infrastructure.api.dto.CategoryDto;
import com.snapp.spendtracker.exception.InvalidInputDataException;
import com.snapp.spendtracker.infrastructure.domain.SpendingCategoryEntity;
import com.snapp.spendtracker.infrastructure.repository.UserRepository;
import com.snapp.spendtracker.infrastructure.domain.UserInformationEntity;
import com.snapp.spendtracker.infrastructure.repository.CategoryRepository;
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
    private final UserRepository userService;

    public String addNewCategory(AddCategoryCommand categoryDto) {
        var user = userService.loadUserByUserName(requestInfo.getUserName());
        checkDuplicateCategories(categoryDto.name(), user);
        var category = SpendingCategoryEntity
            .builder()
            .user(user)
            .limitAmount(categoryDto.limit())
            .name(categoryDto.name())
            .build();
        categoryRepository.save(category);
        return "Added Successfully.";
    }

    @Transactional(readOnly = true)
    public Page<CategoryDto> retrieveCategories(SearchCategoryQuery searchCategoryQuery) {
        var user = userService.loadUserByUserName(requestInfo.getUserName());
        Page<SpendingCategoryEntity> paginatedUserCategories = categoryRepository
            .findAllByNameContainingIgnoreCaseAndUser(searchCategoryQuery.name(), user,
                PageRequest.of(searchCategoryQuery.page(), searchCategoryQuery.pageSize()));
        if (!paginatedUserCategories.isEmpty()){
            return paginatedUserCategories
                .map(categoryMapper::map);
        }
        return Page.empty();
    }
    public void checkDuplicateCategories(String categoryName, UserInformationEntity user){
        if(!categoryRepository.findAllByNameAndUser_Id(categoryName, user.getId()).isEmpty()){
            throw new InvalidInputDataException("This category is exist for you.Please Enter a new!");
        }
    }

}
