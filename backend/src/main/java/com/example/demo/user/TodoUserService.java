package com.example.demo.user;

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

    public Optional<TodoUser> findByUsername(String username) {
        return todoUserRepo.findByUsername(username);
    }

    public Optional<TodoUser> findById(String id) {
        return todoUserRepo.findById(id);
    }
}
