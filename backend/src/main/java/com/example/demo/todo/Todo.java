package com.example.demo.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    public Todo(String task, String description) {
        this.task = task;
        this.description = description;
    }

    @Id
    private String id;
    private TodoStatus status = TodoStatus.OPEN;
    private String task;
    private String description;
    private String userid;
}
