package com.mod.todo.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TodoRequestDto {
    private String todoYear;
    private String todoMonth;
    private String todoDay;
    private String todoContent;
}
