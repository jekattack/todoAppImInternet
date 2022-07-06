package com.example.demo.user;

import com.example.demo.todo.Todo;
import com.example.demo.todo.TodoRepository;
import com.example.demo.todo.TodoService;
import com.example.demo.todo.TodoStatus;
import org.apache.catalina.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class TodoUserServiceTest {

    @Test
    void shouldAddUser(){
        TodoUserRepo testTodoUserRepo = Mockito.mock(TodoUserRepo.class);
        PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
        Mockito.when(passwordEncoder.encode("Password")).thenReturn("hashedPassword");

        //Given
        UserCreationData userCreationData = new UserCreationData( "NameOfThePerson", "Password");
        TodoUserService testTodoUserService = new TodoUserService(testTodoUserRepo, passwordEncoder);

        //When
        TodoUser expectedUser = new TodoUser();
        expectedUser.setUsername("NameOfThePerson");
        expectedUser.setPassword("hashedPassword");
        expectedUser.setRole("user");

        testTodoUserService.createUser(userCreationData);


        //Then
        Mockito.verify(testTodoUserRepo).save(expectedUser);

    }

    @Test
    void shouldCreateNewUser() {
        // Given
        TodoUserRepo userRepository = Mockito.mock(TodoUserRepo.class);
        PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
        Mockito.when(passwordEncoder.encode("password")).thenReturn("hashedPassword");

        UserCreationData userCreationData = new UserCreationData("testUser", "password");
        TodoUserService userService = new TodoUserService(userRepository, passwordEncoder);

        TodoUser expectedUser = new TodoUser();
        expectedUser.setUsername("testUser");
        expectedUser.setPassword("hashedPassword");
        expectedUser.setRole("user");

        // when
        userService.createUser(userCreationData);

        // then
        Mockito.verify(userRepository).save(expectedUser);
    }

    @Test
    void shouldNotCreateNewUser_usernameIsBlank() {
        // Given
        UserCreationData userCreationData = new UserCreationData(" ", "password");
        TodoUserService userService = new TodoUserService(null, null);

        // when
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> userService.createUser(userCreationData))
                .withMessage("Leerer Username, du Vogel.");
    }

    @Test
    void shouldNotCreateNewUser_usernameIsNull() {
        // Given
        UserCreationData userCreationData = new UserCreationData(null, "password");
        TodoUserService userService = new TodoUserService(null, null);

        // when
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> userService.createUser(userCreationData))
                .withMessage("Leerer Username, du Vogel.");
    }
    
}