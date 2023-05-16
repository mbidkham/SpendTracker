package com.snapp.spendtracker.repository;

import com.snapp.spendtracker.infrastructure.domain.SpendingCategoryEntity;
import com.snapp.spendtracker.infrastructure.domain.UserInformationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<SpendingCategoryEntity, Long>
{
    Optional<SpendingCategoryEntity> findByIdAndUser(Long id, UserInformationEntity userInformation);
    List<SpendingCategoryEntity> findAllByNameAndUser_Id(String name, Long userId);
    Page<SpendingCategoryEntity> findAllByNameContainingIgnoreCaseAndUser(String name, UserInformationEntity userInformation,
                                                                          Pageable pageable);





}
