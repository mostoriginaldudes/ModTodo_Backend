package com.mod.todo.service;

import com.mod.todo.domain.todo.Todo;
import com.mod.todo.domain.todo.TodoRepo;
import com.mod.todo.domain.user.User;
import com.mod.todo.domain.user.UserRepo;
import com.mod.todo.dtos.TodoRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService{

    @Autowired
    TodoRepo todoRepo;
    @Autowired
    UserRepo userRepo;

    @Override
    @Transactional
    public List<Todo> getList(long userId, String month) {
        User user = userRepo.findAllByUserId(userId);
        List<Todo> todos = todoRepo.findAllByUserAndTodoMonth(user,month);
        return todos;
    }

    @Override
    public Long insert(long userId, TodoRequestDto todoDto) {
        User user = userRepo.findAllByUserId(userId);
        Todo todo = Todo.builder()
                .todoDay(todoDto.getTodoDay())
                .todoMonth(todoDto.getTodoMonth())
                .todoYear(todoDto.getTodoYear())
                .user(user)
                .todoContent(todoDto.getTodoContent())
                .build();
        return todoRepo.save(todo).getTodoId();
    }

    @Override
    @Transactional
    public Long update(long todoId, String todoContent) {
        Todo todo = todoRepo.findByTodoId(todoId);
        todo.setTodoContent(todoContent);
        return todoId;
    }

    @Override
    public Long delete(long todoId) {
        Todo todo = todoRepo.findByTodoId(todoId);
        todoRepo.delete(todo);
        return todoId;
    }


}
