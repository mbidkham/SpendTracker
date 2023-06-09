package com.snapp.spendtracker.repository;

import com.snapp.spendtracker.model.SpendingCategory;
import com.snapp.spendtracker.model.UserInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<SpendingCategory, Long>
{
    Optional<SpendingCategory> findByIdAndUser(Long id, UserInformation userInformation);
    List<SpendingCategory> findAllByNameAndUser_Id(String name, Long userId);
    Page<SpendingCategory> findAllByNameContainingIgnoreCaseAndUser(String name, UserInformation userInformation,
                                                                    Pageable pageable);

    Page<SpendingCategory> findAllByUser_Id(Long userId, Pageable pageable);


}
