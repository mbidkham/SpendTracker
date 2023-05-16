package com.snapp.spendtracker.infrastructure.repository;

import com.snapp.spendtracker.core.domain.UserInfo;
import com.snapp.spendtracker.infrastructure.domain.UserInformationEntity;
import com.snapp.spendtracker.repository.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.snapp.spendtracker.exception.InvalidInputDataException;

@Service
@RequiredArgsConstructor
public class UserRepository implements UserInfo {
    private final UserInformationRepository userInformationRepository;

    public UserInformationEntity loadUserByUserName(String userName){
        return userInformationRepository.findByUserName(userName)
            .orElseThrow(() -> new InvalidInputDataException("User not found."));
    }

}
