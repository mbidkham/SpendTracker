package com.snapp.spendtracker.controller;

import com.snapp.spendtracker.controller.dto.LoginUserDto;
import com.snapp.spendtracker.service.AuthenticationManager;
import com.snapp.spendtracker.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.authentication.BadCredentialsException;

@Controller
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginUserDto userInfo){
        String userName ;
        try {
            userName = authenticationManager.authenticate(userInfo);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        final String token = JwtTokenUtil.generateToken(userName);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private record JwtResponse(String token) {
    }
}
