package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private TodoService testTodoService;

    @Test
    void shouldReturnGivenTodo(){
        //Given
        Todo todo = new Todo(TodoStatus.IN_PROGRESS, "Wäsche waschen", "Kleidung säubern");
        when(testTodoService.getTodo(todo.getId())).thenReturn(todo);

        TodoController todoController = new TodoController(testTodoService);

        //When
        Todo actual = todoController.getTodo(todo.getId());

        //Then
        assertThat(actual).isEqualTo(todo);
    }

    @Test
    void shouldRunAddTodo(){
        //Given
        Todo todo = new Todo(TodoStatus.IN_PROGRESS, "Wäsche waschen", "Kleidung säubern");

        TodoController todoController = new TodoController(testTodoService);

        //When
        todoController.createTodo(todo);

        //Then
        verify(testTodoService).createTodo(todo);
    }

    @Test
    void shouldChangeStatusOfTodo(){
        //Given
        Todo todo = new Todo(TodoStatus.IN_PROGRESS, "Wäsche waschen", "Kleidung säubern");

        TodoController todoController = new TodoController(testTodoService);

        //When
        todoController.changeNext(todo);

        //Then
        verify(testTodoService).changeNext(todo);
    }

}