package com.snapp.spendtracker.infrastructure.repository;

import com.snapp.spendtracker.infrastructure.domain.UserInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformationEntity, Long> {

    Optional<UserInformationEntity> findByUserName(String userName);
}
