package com.snapp.spendtracker.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "USER_INFO")
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String userName;
    private String password;
    private UserRole userRole;
}
