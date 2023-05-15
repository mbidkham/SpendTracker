package com.snapp.spendtracker.controller.dto;


import com.snapp.spendtracker.exception.InvalidInputDataException;


public record LoginUserDto (String userName, String password){

    public LoginUserDto {
        if (userName == null || userName.isEmpty()) {
            throw new InvalidInputDataException("Please Enter userName.");
        }
        if (password == null || password.isEmpty()) {
            throw new InvalidInputDataException("Please Enter password.");
        }
    }
}
