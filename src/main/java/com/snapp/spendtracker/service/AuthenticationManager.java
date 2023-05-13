package com.snapp.spendtracker.service;

import com.snapp.spendtracker.controller.dto.LoginUserDto;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

@Component()
public class AuthenticationManager {

    public String authenticate(LoginUserDto userDto) throws BadCredentialsException {



    }
}
