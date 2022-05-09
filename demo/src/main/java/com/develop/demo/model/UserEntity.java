package com.develop.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="users",uniqueConstraints = {@UniqueConstraint(columnNames = "user_id")})
public class UserEntity {

    @Id @GeneratedValue
    @Column(name="id")
    private Long id;


    @Column(name="user_id",nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String role; //ROLE_USER,ROLE_ADMIN
}
