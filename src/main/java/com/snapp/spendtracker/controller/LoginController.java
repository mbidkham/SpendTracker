package com.snapp.spendtracker.controller;

import com.snapp.spendtracker.controller.dto.LoginUserDto;
import com.snapp.spendtracker.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginUserDto userInfo) {
        return ResponseEntity.ok(new JwtResponse(loginService.login(userInfo)));
    }

    private record JwtResponse(String token) {
    }
}
