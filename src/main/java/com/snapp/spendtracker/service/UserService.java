package com.snapp.spendtracker.service;

import com.snapp.spendtracker.model.UserInformation;
import com.snapp.spendtracker.repository.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserInformationRepository userInformationRepository;

    public UserInformation loadUserByUserName(String userName){
        return userInformationRepository.findByUserName(userName).orElseThrow();
    }

}
