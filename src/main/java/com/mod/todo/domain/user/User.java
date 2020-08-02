package com.mod.todo.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_phone")
    private String userPhoneNumber;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_name")
    private String userName;

    @Builder
    public User(String userPhoneNumber, String userPassword, String userName){
        this.userPhoneNumber = userPhoneNumber;
        this.userPassword = userPassword;
        this.userName = userName;
    }
}
