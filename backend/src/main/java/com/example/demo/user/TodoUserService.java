package com.example.demo.user;

import com.example.demo.security.oauth.GitHubUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoUserService {

    private final TodoUserRepo todoUserRepo;
    private final PasswordEncoder passwordEncoder;

    public void createUser(UserCreationData userCreationData) {
        if(userCreationData.getUsername()==null || userCreationData.getUsername().isBlank()) throw new IllegalArgumentException("Leerer Username, du Vogel.");
        TodoUser todoUser = new TodoUser(userCreationData.getUsername(), userCreationData.getPassword());
        todoUser.setPassword(passwordEncoder.encode(todoUser.getPassword()));
        todoUser.setRole("user");
        todoUserRepo.save(todoUser);
    }

    public TodoUser createOrGet(GitHubUser gitHubUser) {
        return todoUserRepo.findByGitHubUserId(gitHubUser.getId())
                .orElseGet(() -> {
                    TodoUser user = new TodoUser();
                    user.setGitHubUserId(gitHubUser.getId());
                    user.setUsername(gitHubUser.getLogin());
                    user.setRole("user");
                    return todoUserRepo.save(user);
                });
    }


    public Optional<TodoUser> findByUsername(String username) {
        return todoUserRepo.findByUsername(username);
    }

    public Optional<TodoUser> findById(String id) {
        return todoUserRepo.findById(id);
    }
}
