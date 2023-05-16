package com.snapp.spendtracker.infrastructure.api;

import com.snapp.spendtracker.application.service.command.AddCategoryCommand;
import com.snapp.spendtracker.application.service.query.SearchCategoryQuery;
import com.snapp.spendtracker.infrastructure.api.dto.AddCategoryDto;
import com.snapp.spendtracker.infrastructure.api.dto.CategoryDto;
import com.snapp.spendtracker.infrastructure.api.dto.SearchCategoryDto;
import com.snapp.spendtracker.application.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping(value = "/category")
@RestController
@RequiredArgsConstructor
public class CategoryResource {

    private final CategoryService categoryService;
    @PostMapping(value = "/add")
    public ResponseEntity<String> insert(@RequestBody AddCategoryCommand categoryDto){
        return ResponseEntity.ok(categoryService.addNewCpomategory(categoryDto));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<CategoryDto>> search(@RequestBody SearchCategoryQuery searchCategoryDto){
        return ResponseEntity.ok(categoryService.retrieveCategories(searchCategoryDto));
    }
}
