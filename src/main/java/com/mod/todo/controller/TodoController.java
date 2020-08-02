package com.mod.todo.controller;


import com.mod.todo.domain.todo.Todo;
import com.mod.todo.dtos.TodoRequestDto;
import com.mod.todo.dtos.TodoResponseDto;
import com.mod.todo.dtos.TodoUpdateRequestDto;
import com.mod.todo.error.UnauthorizedException;
import com.mod.todo.service.JwtService;
import com.mod.todo.service.TodoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class TodoController {

    @Autowired
    TodoService todoService;

    @Autowired
    JwtService jwtService;

    //해당 월의 모든 todoList
    @ApiOperation(value = "해당 월의 모든 투두")
    @GetMapping("/todo/{month}")
    public ResponseEntity<List<TodoResponseDto>> getList(@PathVariable String month) {
        List<TodoResponseDto> todos = new ArrayList<>();
        try {
            Object obj = jwtService.get("userId");
            long userId = Long.parseLong(String.valueOf(obj));
            List<Todo> list = todoService.getList(userId, month);
            for (Todo todo : list) {
                TodoResponseDto todoResponseDto = TodoResponseDto.builder()
                        .todoYear(todo.getTodoYear())
                        .todoMonth(todo.getTodoMonth())
                        .todoId(todo.getTodoId())
                        .todoContent(todo.getTodoContent())
                        .todoDay(todo.getTodoDay())
                        .build();
                todos.add(todoResponseDto);
            }
        }catch (UnauthorizedException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        for(TodoResponseDto todo: todos){
            System.out.println(todo.getTodoContent());
        }
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    //insert
    @PostMapping("/todo")
    @ApiOperation(value = "투두리스트 Insert")
    public ResponseEntity<Long> insert(@RequestBody TodoRequestDto todoRequestDto){
        try {
            Object obj = jwtService.get("userId");
            long userId = Long.parseLong(String.valueOf(obj));
            long todoId = todoService.insert(userId,todoRequestDto);
            return new ResponseEntity<>(todoId,HttpStatus.OK);
        } catch (UnauthorizedException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //update
    @ApiOperation("투두업데이트")
    @PatchMapping("/todo/{todoId}")
    public ResponseEntity<Long> update(@PathVariable Long todoId,@RequestBody TodoUpdateRequestDto todoUpdateRequestDto) {
        System.out.println("=========================="+todoUpdateRequestDto.getTodoContent());
        try {
            long todoid = todoService.update(todoId,todoUpdateRequestDto.getTodoContent());
            return new ResponseEntity<>(todoid, HttpStatus.OK);
        } catch (UnauthorizedException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("투두 딜리트")
    @DeleteMapping("/todo/{todoId}")
    public ResponseEntity<Long> delete(@PathVariable Long todoId){
        try{
            todoService.delete(todoId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (UnauthorizedException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
