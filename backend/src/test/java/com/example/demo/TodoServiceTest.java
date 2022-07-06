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

    @Test
    void shouldAddTask(){
        //Given
        Todo testTodo = new Todo("1236", TodoStatus.OPEN, "Schuhe kaufen", "Beim Schuhladen", "1234567890");
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
        Todo testTodo1 = new Todo("1236", TodoStatus.DONE, "Gemüse kaufen", "Beim Schuhladen", "1");

        TodoRepository testTodoRepository = Mockito.mock(TodoRepository.class);
        TodoService testTodoService = new TodoService(testTodoRepository);

        //When
        Mockito.when(testTodoRepository.findAllByUserid("1")).thenReturn(List.of(testTodo1));

        //Then
        Assertions.assertThat(testTodoService.listTodos("1")).contains(testTodo1);
    }

    @Test
    void shouldEditTodo(){
        //Given
        Todo testTodo1 = new Todo("1239", TodoStatus.DONE, "Schuhe kaufen", "Beim Schuhladen", "1");

        TodoRepository testTodoRepository = Mockito.mock(TodoRepository.class);
        TodoService testTodoService = new TodoService(testTodoRepository);

        //When
        testTodoService.editTodo(testTodo1);

        //Then
        verify(testTodoRepository).save(testTodo1);
    }

    //get einzeln

    @Test
    void shouldReturnOneTodo(){
        //Given
        Todo testTodo1 = new Todo("1239", TodoStatus.DONE, "Schuhe kaufen", "Beim Schuhladen", "1");

        TodoRepository testTodoRepository = Mockito.mock(TodoRepository.class);
        TodoService testTodoService = new TodoService(testTodoRepository);

        //When
        Mockito.when(testTodoRepository.findTodoById("1239")).thenReturn(testTodo1);

        //Then
        Assertions.assertThat(testTodoService.getTodo("1239")).isEqualTo(testTodo1);
    }

    //löschen

    @Test
    void shouldDeleteOneTodo(){
        //Given
        Todo testTodo1 = new Todo("1239", TodoStatus.DONE, "Schuhe kaufen", "Beim Schuhladen", "1");

        TodoRepository testTodoRepository = Mockito.mock(TodoRepository.class);
        TodoService testTodoService = new TodoService(testTodoRepository);

        //When
        testTodoService.delete("1239");
        //Then
        verify(testTodoRepository).deleteById("1239");
    }

    //change next

    @Test
    void shouldChangeStatusNext(){
        //Given
        Todo testTodo1 = new Todo("1239", TodoStatus.IN_PROGRESS, "Schuhe kaufen", "Beim Schuhladen", "1");
        Todo expectedTestTodo1 = new Todo("1239", TodoStatus.DONE, "Schuhe kaufen", "Beim Schuhladen", "1");

        TodoRepository testTodoRepository = Mockito.mock(TodoRepository.class);
        TodoService testTodoService = new TodoService(testTodoRepository);

        //When
        testTodoService.changeNext(testTodo1);

        //Then
        verify(testTodoRepository).save(expectedTestTodo1);
    }

    //change prev

    @Test
    void shouldChangeStatusPrev(){
        //Given
        Todo testTodo1 = new Todo("1239", TodoStatus.IN_PROGRESS, "Schuhe kaufen", "Beim Schuhladen", "1");
        Todo expectedTestTodo1 = new Todo("1239", TodoStatus.OPEN, "Schuhe kaufen", "Beim Schuhladen", "1");

        TodoRepository testTodoRepository = Mockito.mock(TodoRepository.class);
        TodoService testTodoService = new TodoService(testTodoRepository);

        //When
        testTodoService.changePrev(testTodo1);

        //Then
        verify(testTodoRepository).save(expectedTestTodo1);
    }

}