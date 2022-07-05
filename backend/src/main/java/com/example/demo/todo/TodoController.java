package com.example.demo.todo;

import java.security.Principal;
import java.util.List;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.demo.user.TodoUser;
import com.example.demo.user.TodoUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/kanban")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final TodoUserService todoUserService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Todo> getAllTodos(Principal principal) {
        TodoUser user = todoUserService.findByUsername(principal.getName()).orElseThrow();
        return todoService.listTodos(user.getId());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createTodo(@RequestBody Todo todo, Principal principal){
        TodoUser user = todoUserService.findByUsername(principal.getName()).orElseThrow();
        String userid = user.getId();
        todo.setUserid(userid);
        todoService.createTodo(todo);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Todo getTodo(@PathVariable String id) {
        return todoService.getTodo(id);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void editTodo(@RequestBody Todo todo, Principal principal) {
        TodoUser user = todoUserService.findByUsername(principal.getName()).orElseThrow();
        String userid = user.getId();
        todo.setUserid(userid);
        todoService.editTodo(todo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable String id){
        todoService.delete(id);
    }

    @PutMapping("/next")
    @ResponseStatus(HttpStatus.OK)
    public void changeNext(@RequestBody Todo todo){
        todoService.changeNext(todo);
    }

    @PutMapping("/prev")
    @ResponseStatus(HttpStatus.OK)
    public void changePrev(@RequestBody Todo todo){
        todoService.changePrev(todo);
    }

}