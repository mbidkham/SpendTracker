package com.snapp.spendtracker.infrastructure.domain;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
public class UserCategoryEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private Long userId;
    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "id", insertable = false, updatable = false)
    @ToString.Exclude
    private UserInformationEntity user;

    @OneToOne
    @JoinColumn(name = "CATEGORY", referencedColumnName = "id")
    private SpendingCategoryEntity category;


}
