package com.example.demo;

import com.example.demo.todo.Todo;
import com.example.demo.todo.TodoStatus;
import com.example.demo.user.LoginData;
import com.example.demo.user.LoginResponse;
import com.example.demo.user.UserCreationData;
import org.apache.coyote.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.messaging.Task;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoApplicationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void integrationTest(){

        //Registrierung
        UserCreationData testUserCreationData = new UserCreationData("Angela", "bundestagsSauna");
        ResponseEntity<Void> creationResponse = testRestTemplate.postForEntity("/api/user", testUserCreationData, Void.class);

        Assertions.assertThat(creationResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);


        //Login
        LoginData testUserLoginData = new LoginData("Angela", "bundestagsSauna");
        ResponseEntity<LoginResponse> loginResponse = testRestTemplate.postForEntity("/api/login", testUserLoginData, LoginResponse.class);

        Assertions.assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(loginResponse.getBody().getToken()).isNotEmpty();


        //Load empty List for testuser
        String token = loginResponse.getBody().getToken();

        ResponseEntity<Todo[]> emptyGetAllResponse = testRestTemplate.exchange(
                "/api/kanban",
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(token)),
                Todo[].class
        );
        Assertions.assertThat(emptyGetAllResponse.getBody()).isEmpty();


        //Create new Todoitem
        Todo firstTodoInput = new Todo("Gewichte heben", "Mit den Füßen");
        ResponseEntity<Void> firstTodoPostResponse = testRestTemplate.exchange(
                "/api/kanban",
                HttpMethod.POST,
                new HttpEntity<>(firstTodoInput, createHeaders(token)),
                Void.class
        );
        Assertions.assertThat(firstTodoPostResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);


        //Get List with new Todoitem
        ResponseEntity<Todo[]> firstGetAllResponse = testRestTemplate.exchange(
                "/api/kanban",
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(token)),
                Todo[].class
        );
        Assertions.assertThat(firstGetAllResponse.getBody()[0].getTask()).isEqualTo(firstTodoInput.getTask());
        Assertions.assertThat(firstGetAllResponse.getBody()[0].getDescription()).isEqualTo(firstTodoInput.getDescription());


        //Change Status of Todoitem to IN_PROGRESS
        Todo firstTodo = firstGetAllResponse.getBody()[0];

        ResponseEntity<Void> changeNextFirstTodoResponse = testRestTemplate.exchange(
                "/api/kanban/next",
                HttpMethod.PUT,
                new HttpEntity<>(firstTodo, createHeaders(token)),
                Void.class
        );
        Assertions.assertThat(changeNextFirstTodoResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<Todo> getFirstTodoResponse = testRestTemplate.exchange(
                "/api/kanban/" + firstTodo.getId(),
                HttpMethod.GET,
                new HttpEntity<>(firstTodo, createHeaders(token)),
                Todo.class
        );
        firstTodo.setStatus(TodoStatus.IN_PROGRESS);
        Assertions.assertThat(getFirstTodoResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(getFirstTodoResponse.getBody()).isEqualTo(firstTodo);


        //Change Status of Todoitem to DONE
        ResponseEntity<Void> changeNextFirstTodoResponse2 = testRestTemplate.exchange(
                "/api/kanban/next",
                HttpMethod.PUT,
                new HttpEntity<>(firstTodo, createHeaders(token)),
                Void.class
        );
        Assertions.assertThat(changeNextFirstTodoResponse2.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<Todo> getFirstTodoResponse2 = testRestTemplate.exchange(
                "/api/kanban/" + firstTodo.getId(),
                HttpMethod.GET,
                new HttpEntity<>(firstTodo, createHeaders(token)),
                Todo.class
        );
        firstTodo.setStatus(TodoStatus.DONE);
        Assertions.assertThat(getFirstTodoResponse2.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(getFirstTodoResponse2.getBody()).isEqualTo(firstTodo);

        //Assert that further progress has no effect
        ResponseEntity<Void> changeNextFirstTodoResponse3 = testRestTemplate.exchange(
                "/api/kanban/next",
                HttpMethod.PUT,
                new HttpEntity<>(firstTodo, createHeaders(token)),
                Void.class
        );
        Assertions.assertThat(changeNextFirstTodoResponse3.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<Todo> getFirstTodoResponse3 = testRestTemplate.exchange(
                "/api/kanban/" + firstTodo.getId(),
                HttpMethod.GET,
                new HttpEntity<>(firstTodo, createHeaders(token)),
                Todo.class
        );
        Assertions.assertThat(getFirstTodoResponse3.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(getFirstTodoResponse3.getBody()).isEqualTo(firstTodo);

        //Change Status of Todoitem back to IN_PROGRESS
        ResponseEntity<Void> changePrevFirstTodoResponse = testRestTemplate.exchange(
                "/api/kanban/prev",
                HttpMethod.PUT,
                new HttpEntity<>(firstTodo, createHeaders(token)),
                Void.class
        );
        Assertions.assertThat(changePrevFirstTodoResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<Todo> getFirstTodoResponse4 = testRestTemplate.exchange(
                "/api/kanban/" + firstTodo.getId(),
                HttpMethod.GET,
                new HttpEntity<>(firstTodo, createHeaders(token)),
                Todo.class
        );
        firstTodo.setStatus(TodoStatus.IN_PROGRESS);
        Assertions.assertThat(getFirstTodoResponse4.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(getFirstTodoResponse4.getBody()).isEqualTo(firstTodo);

        //Edit Todoitem Task & Description
        firstTodo.setTask("Mehr Gewichte heben");
        firstTodo.setDescription("Mit Händen und Füßen");

        ResponseEntity<Void> editTodoResponse = testRestTemplate.exchange(
                "/api/kanban/",
                HttpMethod.PUT,
                new HttpEntity<>(firstTodo, createHeaders(token)),
                Void.class
        );
        Assertions.assertThat(editTodoResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<Todo> getFirstTodoResponse5 = testRestTemplate.exchange(
                "/api/kanban/" + firstTodo.getId(),
                HttpMethod.GET,
                new HttpEntity<>(firstTodo, createHeaders(token)),
                Todo.class
        );
        Assertions.assertThat(getFirstTodoResponse5.getBody().getTask()).isEqualTo("Mehr Gewichte heben");
        Assertions.assertThat(getFirstTodoResponse5.getBody().getDescription()).isEqualTo("Mit Händen und Füßen");

        //Progress Todoitem to DONE and Delete it
        ResponseEntity<Void> changeNextFirstTodoResponse4 = testRestTemplate.exchange(
                "/api/kanban/next",
                HttpMethod.PUT,
                new HttpEntity<>(firstTodo, createHeaders(token)),
                Void.class
        );
        ResponseEntity<Todo> getFirstTodoResponse6 = testRestTemplate.exchange(
                "/api/kanban/" + firstTodo.getId(),
                HttpMethod.GET,
                new HttpEntity<>(firstTodo, createHeaders(token)),
                Todo.class
        );
        Assertions.assertThat(getFirstTodoResponse6.getBody().getStatus()).isEqualTo(TodoStatus.DONE);

        ResponseEntity<Void> deleteFirstTodoResponse = testRestTemplate.exchange(
                "/api/kanban/" + firstTodo.getId(),
                HttpMethod.DELETE,
                new HttpEntity<>(firstTodo, createHeaders(token)),
                Void.class
        );
        Assertions.assertThat(deleteFirstTodoResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<Todo[]> emptyGetAllResponse2 = testRestTemplate.exchange(
                "/api/kanban",
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(token)),
                Todo[].class
        );
        Assertions.assertThat(emptyGetAllResponse.getBody()).isEmpty();

    }

    public HttpHeaders createHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        return headers;
    }

}