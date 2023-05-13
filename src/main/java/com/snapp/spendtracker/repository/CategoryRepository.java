package com.snapp.spendtracker.repository;

import com.snapp.spendtracker.model.SpendingCategory;
import com.snapp.spendtracker.model.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<SpendingCategory, Long>
{
    List<SpendingCategory> findAllByNameAndUser(String name, UserInformation userInformation);
    List<SpendingCategory> findAllByUser(UserInformation userInformation);


}
