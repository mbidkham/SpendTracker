package com.snapp.spendtracker.controller;

import com.snapp.spendtracker.controller.dto.AddCategoryDto;
import com.snapp.spendtracker.controller.dto.CategoryDto;
import com.snapp.spendtracker.controller.dto.SearchCategoryDto;
import com.snapp.spendtracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping(value = "/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @PostMapping(value = "/add")
    public ResponseEntity<String> insert(@RequestBody AddCategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.addNewCategory(categoryDto));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<CategoryDto>> search(@RequestBody SearchCategoryDto searchCategoryDto){
        return ResponseEntity.ok(categoryService.retrieveCategories(searchCategoryDto));
    }

    @GetMapping("/{page}/{pageSize}")
    public ResponseEntity<Page<CategoryDto>> getAllUserCategories(@PathVariable("page") int page,
                                                                  @PathVariable("pageSize") int pageSize){
        return ResponseEntity.ok(categoryService.getAllUserCategories(page, pageSize));
    }
}
