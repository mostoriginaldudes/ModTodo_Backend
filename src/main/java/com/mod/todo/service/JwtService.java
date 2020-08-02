package com.mod.todo.service;

import com.mod.todo.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface JwtService {
    String create(User user);
    boolean isUsable(String jwt) throws Exception;
    Object get(String key) throws Exception;
}
