package com.mod.todo.domain.todo;

import com.mod.todo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepo extends JpaRepository<Todo,Long> {
    List<Todo> findAllByUser(User user);
    List<Todo> findAllByUserAndTodoMonth(User user,String todoMonth);
    Todo findByTodoId(Long todoId);
}
