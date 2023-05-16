package com.snapp.spendtracker.infrastructure.api;

import com.snapp.spendtracker.infrastructure.api.dto.AddCategoryDto;
import com.snapp.spendtracker.infrastructure.api.dto.CategoryDto;
import com.snapp.spendtracker.infrastructure.api.dto.SearchCategoryDto;
import com.snapp.spendtracker.service.CategoryService;
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
    public ResponseEntity<String> insert(@RequestBody AddCategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.addNewCategory(categoryDto));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<CategoryDto>> search(@RequestBody SearchCategoryDto searchCategoryDto){
        return ResponseEntity.ok(categoryService.retrieveCategories(searchCategoryDto));
    }
}
