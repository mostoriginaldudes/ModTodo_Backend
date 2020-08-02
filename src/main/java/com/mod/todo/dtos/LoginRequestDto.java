package com.mod.todo.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LoginRequestDto {
    public String userPhoneNumber;
    public String userPassword;
}
