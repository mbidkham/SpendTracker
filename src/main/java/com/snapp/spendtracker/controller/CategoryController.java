package com.snapp.spendtracker.controller;

import com.snapp.spendtracker.controller.dto.AddCategoryDto;
import com.snapp.spendtracker.controller.dto.CategoryDto;
import com.snapp.spendtracker.controller.dto.SearchCategoryDto;
import com.snapp.spendtracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "/category")
@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @PostMapping(value = "/add")
    public ResponseEntity<String> insert(@RequestBody AddCategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.addNewCategory(categoryDto));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<CategoryDto>> search(@RequestBody SearchCategoryDto searchCategoryDto){
        return ResponseEntity.ok(categoryService.retrieveCategories(searchCategoryDto));
    }
}
