package com.snapp.spendtracker.infrastructure.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "USER_INFO")
public class UserInformationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String userName;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
