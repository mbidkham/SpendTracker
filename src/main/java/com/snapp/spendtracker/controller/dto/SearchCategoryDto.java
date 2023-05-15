package com.snapp.spendtracker.controller.dto;


import javax.validation.constraints.NotNull;

public record SearchCategoryDto(@NotNull(message = "Please Enter category name.") String name, int page, int pageSize) {

}
