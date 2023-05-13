package com.snapp.spendtracker.service;

import com.snapp.spendtracker.controller.dto.LoginUserDto;
import com.snapp.spendtracker.repository.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component()
@RequiredArgsConstructor
public class AuthenticationManager {

    @Value(value = "${app.authentication.salt-before}")
    private String saltBefore;
    @Value(value = "${app.authentication.salt-after}")
    private String saltAfter;

    private final MessageDigest digest;
    private final UserInformationRepository userInformationRepository;

    public String authenticate(LoginUserDto userDto) throws BadCredentialsException {
        var userInfo = userInformationRepository.findByUserName(userDto.userName()).orElseThrow();
        if (userInfo.getPassword().equals(hashPassword(userDto.password()))){
            return userInfo.getUserName();
        }
        else {
            throw new BadCredentialsException("Password is not valid.");
        }

    }

    String hashPassword(String password) {
        String saltedPassword = saltBefore + password + saltAfter;
        byte[] hashedPassword = digest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
        return new String(Hex.encode(hashedPassword));
    }
}
