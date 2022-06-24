package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepo todoRepo;

    public List<Todo> listTodos(){
        return todoRepo.list();
    }

    public Todo getTodo(String id){
        return todoRepo.get(id).orElseThrow();
    }

    public void createTodo(Todo todo) {
        if (!"".equals(todo.getTask())) {
            todoRepo.create(todo);
        } else {
            throw new Error(String.valueOf(HttpStatus.BAD_REQUEST));
        }
    }

    public void editTodo(Todo todo){
        todoRepo.edit(todo);
    }

    public void delete(String id){
        todoRepo.delete(id);
    }

    public void changeNext(Todo todo) {
        todo.setStatus(todo.getStatus().progressNext());
        todoRepo.edit(todo);
    }

    public void changePrev(Todo todo) {
        todo.setStatus(todo.getStatus().progressPrev());
        todoRepo.edit(todo);
    }


}
