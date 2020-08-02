package com.mod.todo.dtos;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TodoUpdateRequestDto {
    String todoContent;
}
