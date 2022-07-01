package com.example.demo.todo;

import com.example.demo.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public List<Todo> listTodos(String userid){
        return todoRepository.findAllByUserid(userid);
    }

    public Todo getTodo(String id){
        return todoRepository.findTodoById(id);
    }

    public void createTodo(Todo todo) {
        if (!"".equals(todo.getTask())) {
            todoRepository.save(todo);
        } else {
            throw new Error(String.valueOf(HttpStatus.BAD_REQUEST));
        }
    }

    public void editTodo(Todo todo){
        todoRepository.save(todo);
    }

    public void delete(String id){
        todoRepository.deleteById(id);
    }

    public void changeNext(Todo todo) {
        todo.setStatus(todo.getStatus().progressNext());
        todoRepository.save(todo);
    }

    public void changePrev(Todo todo) {
        todo.setStatus(todo.getStatus().progressPrev());
        todoRepository.save(todo);
    }





}
