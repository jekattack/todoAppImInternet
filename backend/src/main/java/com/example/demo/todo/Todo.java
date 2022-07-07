package com.example.demo.todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {


    @Id
    private String id;
    private TodoStatus status = TodoStatus.OPEN;
    private String task;
    private String description;
    private String userid;
}
