package com.mod.todo.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public class UserDto {
    private String userPhoneNumber;
    private String userPassword;
    private String userName;

    @Builder
    public UserDto(String userPhoneNumber, String password, String userName){
        this.userPhoneNumber = userPhoneNumber;
        this.userPassword = password;
        this.userName = userName;
    }
}
