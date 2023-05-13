package com.snapp.spendtracker.controller;

import com.snapp.spendtracker.controller.dto.AddCategoryDto;
import com.snapp.spendtracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/category")
@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @PostMapping(value = "/add")
    public ResponseEntity<String> insert(@RequestBody AddCategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.addNewCategory(categoryDto));
    }
}
