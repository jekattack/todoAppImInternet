package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TodoRepository extends MongoRepository<Todo, String> {

    Todo findTodoById(String input);

}