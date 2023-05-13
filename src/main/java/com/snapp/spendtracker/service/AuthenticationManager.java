package com.snapp.spendtracker.service;

import com.snapp.spendtracker.controller.dto.LoginUserDto;
import com.snapp.spendtracker.repository.UserInformationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component()
public class AuthenticationManager {

    @Value(value = "${app.authentication.salt-before}")
    private String saltBefore;
    @Value(value = "${app.authentication.salt-after}")
    private String saltAfter;

    private final MessageDigest digest;
    private final UserInformationRepository userInformationRepository;

    public AuthenticationManager(UserInformationRepository userInformationRepository) throws NoSuchAlgorithmException {
        this.digest = MessageDigest.getInstance("SHA-256");
        this.userInformationRepository = userInformationRepository;
    }

    public String authenticate(LoginUserDto userDto) throws BadCredentialsException {
        var userInfo = userInformationRepository.findByUserName(userDto.userName()).orElseThrow();
        if (userInfo.getPassword().equals(hashPassword(userDto.password()))){
            return userInfo.getUserName();
        }
        else {
            throw new BadCredentialsException("Password is not valid.");
        }

    }

    public String hashPassword(String password) {
        String saltedPassword = saltBefore + password + saltAfter;
        byte[] hashedPassword = digest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
        return new String(Hex.encode(hashedPassword));
    }
}
