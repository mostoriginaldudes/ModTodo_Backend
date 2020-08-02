package com.mod.todo.service;

import com.mod.todo.domain.todo.Todo;
import com.mod.todo.dtos.TodoRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TodoService {
    List<Todo> getList(long userId, String month);
    Long insert(long userId, TodoRequestDto todoDto);
    Long update(long todoId,String todoContent);
    Long delete(long todoId);
}
