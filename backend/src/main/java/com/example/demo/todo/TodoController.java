package com.example.demo.todo;

import java.security.Principal;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/kanban")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Todo> getAllTodos(Principal principal) {
        System.out.println(principal);;
        return todoService.listTodos(principal);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createTodo(@RequestBody Todo todo, Principal principal){
        todoService.createTodo(todo, principal);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Todo getTodo(@PathVariable String id) {
        return todoService.getTodo(id);
    }
// Testtest
    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void editTodo(@RequestBody Todo todo) {
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