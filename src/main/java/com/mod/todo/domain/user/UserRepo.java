package com.mod.todo.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findAllByUserPhoneNumber(String userPhone);
    User findAllByUserId(long userId);

}
