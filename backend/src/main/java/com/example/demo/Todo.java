package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "todos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    private String id;
    private TodoStatus status = TodoStatus.OPEN;
    private String task;
    private String description;
}
