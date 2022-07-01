package com.example.demo.todo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodoRepository extends MongoRepository<Todo, String> {

    Todo findTodoById(String input);

    List<Todo> findAllByUserid(String userid);
}