package com.mod.todo.service;

import com.mod.todo.domain.user.User;
import com.mod.todo.domain.user.UserRepo;
import com.mod.todo.dtos.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Override
    public User getUser(String phoneNum) {
        return userRepo.findAllByUserPhoneNumber(phoneNum);
    }

    @Override
    public String signUp(UserDto userDto) {
        User user = User.builder()
                .userName(userDto.getUserName())
                .userPassword(userDto.getUserPassword())
                .userPhoneNumber(userDto.getUserPhoneNumber())
                .build();
        return userRepo.save(user).getUserName();
    }
}
