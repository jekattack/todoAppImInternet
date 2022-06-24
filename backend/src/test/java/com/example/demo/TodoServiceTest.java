package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class TodoServiceTest {

    @Test
    void shouldAddTask(){
        //Given
        Todo testTodo = new Todo("1236", TodoStatus.OPEN, "Schuhe kaufen", "Beim Schuhladen");
        TodoRepository testTodoRepository = Mockito.mock(TodoRepository.class);

        TodoService testTodoService = new TodoService(testTodoRepository);

        //When
        testTodoService.createTodo(testTodo);

        //Then
        Mockito.verify(testTodoRepository).save(testTodo);

    }

    @Test
    void shouldReturnAllTodos(){
        //Given
        Todo testTodo1 = new Todo("1236", TodoStatus.DONE, "Schuhe kaufen", "Beim Schuhladen");
        Todo testTodo2 = new Todo("1237", TodoStatus.IN_PROGRESS, "Schuhe benutzen", "Im Park");
        Todo testTodo3 = new Todo("1238", TodoStatus.OPEN, "Schuhe schmeißen", "Beim Schuhweitwurf");

        TodoRepository testTodoRepository = Mockito.mock(TodoRepository.class);

        TodoService testTodoService = new TodoService(testTodoRepository);

        //When
        Mockito.when(testTodoRepository.findAll()).thenReturn(List.of(testTodo1,testTodo2,testTodo3));

        //Then
        Assertions.assertThat(testTodoService.listTodos()).contains(testTodo1,testTodo2,testTodo3);

    }


    @Test
    void shouldEditTodo(){
        //Given
        Todo testTodo1 = new Todo("1239", TodoStatus.DONE, "Schuhe kaufen", "Beim Schuhladen");

        TodoRepository testTodoRepository = Mockito.mock(TodoRepository.class);

        TodoService testTodoService = new TodoService(testTodoRepository);

        //When
        testTodoService.editTodo(testTodo1);

        //Then
        verify(testTodoRepository).save(testTodo1);

    }

}