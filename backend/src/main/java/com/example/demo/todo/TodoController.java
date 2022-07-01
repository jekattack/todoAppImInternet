package com.example.demo.todo;

import java.util.List;

import com.example.demo.security.JWTService;
import io.jsonwebtoken.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/api/kanban")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final JWTService jwtService;

    private String getToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if(authorization != null){
            return authorization.replace("Bearer", "").trim();
        }
        return null;
    }

    private String getUserid(HttpServletRequest request){
        return jwtService.extractAllClaims(getToken(request)).getSubject();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Todo> getAllTodos(@RequestHeader HttpServletRequest request) {
        return todoService.listTodos(getUserid(request));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createTodo(@RequestBody Todo todo, @RequestHeader HttpServletRequest request){
        todo.setUserid(getUserid(request));

        todoService.createTodo(todo);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Todo getTodo(@PathVariable String id, @RequestHeader HttpServletRequest request) {
        return todoService.getTodo(id);
    }
// Testtest
    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void editTodo(@RequestBody Todo todo, @RequestHeader HttpServletRequest request) {
        todoService.editTodo(todo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable String id, @RequestHeader HttpServletRequest request){
        todoService.delete(id);
    }

    @PutMapping("/next")
    @ResponseStatus(HttpStatus.OK)
    public void changeNext(@RequestBody Todo todo, @RequestHeader HttpServletRequest request){
        todoService.changeNext(todo);
    }

    @PutMapping("/prev")
    @ResponseStatus(HttpStatus.OK)
    public void changePrev(@RequestBody Todo todo, @RequestHeader HttpServletRequest request){
        todoService.changePrev(todo);
    }






}