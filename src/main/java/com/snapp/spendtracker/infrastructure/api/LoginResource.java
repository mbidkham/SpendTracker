package com.snapp.spendtracker.infrastructure.api;

import com.snapp.spendtracker.infrastructure.api.dto.LoginUserDto;
import com.snapp.spendtracker.application.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class LoginResource {

    private final LoginService loginService;

    @PostMapping(value = "/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginUserDto userInfo) {
        return ResponseEntity.ok(new JwtResponse(loginService.login(userInfo)));
    }

    private record JwtResponse(String token) {
    }
}
