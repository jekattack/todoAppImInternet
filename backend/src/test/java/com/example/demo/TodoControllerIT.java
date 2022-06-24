package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void shouldAddAndRemoveTodo(){
        Todo todo = new Todo(TodoStatus.OPEN, "Wäsche waschen", "Kleidung säubern");
        restTemplate.postForEntity("/api/kanban", todo, Void.class);


        restTemplate.delete("/api/kanban/" + todo.getId(), Void.class);

        ResponseEntity<Todo[]> responseEntity1 = restTemplate.getForEntity("/api/kanban", Todo[].class);

        Assertions.assertThat(responseEntity1.getBody()).isEmpty();
    }



    @Test
    void shouldTestWholeApp() {
        //given
        Todo task1 = new Todo(TodoStatus.OPEN, "Holzhacken","Im Wald");
        restTemplate.postForEntity("/api/kanban",task1,Void.class);
        Todo task2 = new Todo(TodoStatus.IN_PROGRESS, "In den Wald fahren","Mit dem Pferdewagen");
        restTemplate.postForEntity("/api/kanban",task2,Void.class);

        ResponseEntity<Todo[]> getResponse = restTemplate.getForEntity("/api/kanban",Todo[].class);

        Assertions.assertThat(getResponse.getBody().length).isEqualTo(2);
        Assertions.assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        restTemplate.put("/api/kanban/next",task1,Void.class);
        ResponseEntity<Todo> getResponse2 = restTemplate.getForEntity("/api/kanban/" + task1.getId(),Todo.class);

        Assertions.assertThat(getResponse2.getBody().getStatus()).isEqualTo(TodoStatus.IN_PROGRESS);

    }




}