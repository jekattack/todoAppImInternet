package com.example.demo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoUser {

    TodoUser(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String password;
    private long gitHubUserId;
    private String role;

}
