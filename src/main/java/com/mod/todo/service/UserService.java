package com.mod.todo.service;

import com.mod.todo.domain.user.User;
import com.mod.todo.dtos.UserDto;
import org.springframework.stereotype.Service;


public interface UserService {
    User getUser(String phoneNum);
    String signUp(UserDto user);
}
