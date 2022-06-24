package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    private final String id = UUID.randomUUID().toString();
    private TodoStatus status = TodoStatus.OPEN;
    private String task;
    private String description;
}
