package com.mod.todo.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TodoResponseDto {
    String todoYear;
    String todoMonth;
    String todoDay;
    String todoContent;
    Long todoId;

    @Builder
    public TodoResponseDto(Long todoId,String todoYear, String todoMonth, String todoDay, String todoContent){
        this.todoYear = todoYear;
        this.todoMonth = todoMonth;
        this.todoId = todoId;
        this.todoDay = todoDay;
        this.todoContent = todoContent;
    }
}
