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
        Todo testTodo = new Todo(TodoStatus.OPEN, "Schuhe kaufen", "Beim Schuhladen");
        TodoRepo testTodoRepo = Mockito.mock(TodoRepo.class);

        TodoService testTodoService = new TodoService(testTodoRepo);

        //When
        testTodoService.createTodo(testTodo);

        //Then
        Mockito.verify(testTodoRepo).create(testTodo);

    }

    @Test
    void shouldReturnAllTodos(){
        //Given
        Todo testTodo1 = new Todo(TodoStatus.DONE, "Schuhe kaufen", "Beim Schuhladen");
        Todo testTodo2 = new Todo(TodoStatus.IN_PROGRESS, "Schuhe benutzen", "Im Park");
        Todo testTodo3 = new Todo(TodoStatus.OPEN, "Schuhe schmeißen", "Beim Schuhweitwurf");

        TodoRepo testTodoRepo = Mockito.mock(TodoRepo.class);

        TodoService testTodoService = new TodoService(testTodoRepo);

        //When
        Mockito.when(testTodoRepo.list()).thenReturn(List.of(testTodo1,testTodo2,testTodo3));

        //Then
        Assertions.assertThat(testTodoService.listTodos()).contains(testTodo1,testTodo2,testTodo3);

    }


    @Test
    void shouldEditTodo(){
        //Given
        Todo testTodo1 = new Todo(TodoStatus.DONE, "Schuhe kaufen", "Beim Schuhladen");

        TodoRepo testTodoRepo = Mockito.mock(TodoRepo.class);

        TodoService testTodoService = new TodoService(testTodoRepo);

        //When
        testTodoService.editTodo(testTodo1);

        //Then
        verify(testTodoRepo).edit(testTodo1);

    }

}