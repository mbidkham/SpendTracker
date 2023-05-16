package com.snapp.spendtracker.infrastructure.api.dto;


import com.snapp.spendtracker.exception.InvalidInputDataException;
import lombok.Builder;

@Builder
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
