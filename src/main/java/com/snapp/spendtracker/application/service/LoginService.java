package com.snapp.spendtracker.application.service;

import com.snapp.spendtracker.infrastructure.api.dto.LoginUserDto;
import com.snapp.spendtracker.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;

    public String login(LoginUserDto userInfo) {
        authenticationManager.authenticate(userInfo);
        return JwtTokenUtil.generateToken(userInfo.userName());
    }

}
