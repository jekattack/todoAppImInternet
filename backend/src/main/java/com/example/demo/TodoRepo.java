package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepo {

    private HashMap<String, Todo> todos = new HashMap<>();

    public void create(Todo todo){
        todos.putIfAbsent(todo.getId(), todo);
    }
    public void edit(Todo todo){
        todos.put(todo.getId(), todo);
    }

    public Optional<Todo> get(String id){
        Optional<Todo> todoReturn = Optional.of(todos.get(id));
        return todoReturn;
    }

    public void delete(String id){
        todos.remove(id);
    }


    public List<Todo> list(){
        List<Todo> listReturn = new ArrayList<>(todos.values());
        return listReturn;
    }


}
