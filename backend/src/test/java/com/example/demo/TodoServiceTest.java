package com.example.demo;

import com.example.demo.todo.Todo;
import com.example.demo.todo.TodoRepository;
import com.example.demo.todo.TodoService;
import com.example.demo.todo.TodoStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.verify;


class TodoServiceTest {

//    @Test
//    void shouldAddTask(){
//        //Given
//        Todo testTodo = new Todo("1236", TodoStatus.OPEN, "Schuhe kaufen", "Beim Schuhladen");
//        TodoRepository testTodoRepository = Mockito.mock(TodoRepository.class);
//
//        TodoService testTodoService = new TodoService(testTodoRepository);
//
//        //When
//        testTodoService.createTodo(testTodo);
//
//        //Then
//        Mockito.verify(testTodoRepository).save(testTodo);
//
//    }
//
//    @Test
//    void shouldReturnAllTodos(){
//        //Given
//        Todo testTodo1 = new Todo("1236", TodoStatus.DONE, "Schuhe kaufen", "Beim Schuhladen");
//        Todo testTodo2 = new Todo("1237", TodoStatus.IN_PROGRESS, "Schuhe benutzen", "Im Park");
//        Todo testTodo3 = new Todo("1238", TodoStatus.OPEN, "Schuhe schmeißen", "Beim Schuhweitwurf");
//
//        TodoRepository testTodoRepository = Mockito.mock(TodoRepository.class);
//
//        TodoService testTodoService = new TodoService(testTodoRepository);
//
//        //When
//        Mockito.when(testTodoRepository.findAll()).thenReturn(List.of(testTodo1,testTodo2,testTodo3));
//
//        //Then
//        Assertions.assertThat(testTodoService.listTodos()).contains(testTodo1,testTodo2,testTodo3);
//
//    }
//
//
//    @Test
//    void shouldEditTodo(){
//        //Given
//        Todo testTodo1 = new Todo("1239", TodoStatus.DONE, "Schuhe kaufen", "Beim Schuhladen");
//
//        TodoRepository testTodoRepository = Mockito.mock(TodoRepository.class);
//
//        TodoService testTodoService = new TodoService(testTodoRepository);
//
//        //When
//        testTodoService.editTodo(testTodo1);
//
//        //Then
//        verify(testTodoRepository).save(testTodo1);
//
//    }

}