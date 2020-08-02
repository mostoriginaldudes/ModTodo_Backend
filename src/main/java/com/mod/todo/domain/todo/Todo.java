package com.mod.todo.domain.todo;

import com.mod.todo.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Todo {

    @Id @GeneratedValue()
    @Column(name = "todo_id")
    private Long todoId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "todo_year")
    private String todoYear;

    @Column(name = "todo_month")
    private String todoMonth;

    @Column(name = "todo_day")
    private String todoDay;

    @Column(name = "content")
    private String todoContent;

    @Builder
    public Todo(User user, String todoYear, String todoMonth, String todoDay,String todoContent){
        this.user = user;
        this.todoYear = todoYear;
        this.todoMonth = todoMonth;
        this.todoDay = todoDay;
        this.todoContent = todoContent;
    }
}
