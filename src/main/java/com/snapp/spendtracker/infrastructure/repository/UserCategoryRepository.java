package com.snapp.spendtracker.infrastructure.repository;

import com.snapp.spendtracker.infrastructure.domain.UserCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategoryEntity, String> {
    Page<UserCategoryEntity> findAllByCategoryNameContainingIgnoreCaseAndUserId(String name, Long userId);


}
