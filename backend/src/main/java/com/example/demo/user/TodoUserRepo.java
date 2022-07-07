package com.example.demo.user;

import com.example.demo.security.oauth.GitHubUser;
import com.mongodb.client.MongoIterable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TodoUserRepo extends MongoRepository<TodoUser, String> {

    Optional<TodoUser> findByUsername(String username);

    Optional<TodoUser> findByGitHubUserId(long id);

}
