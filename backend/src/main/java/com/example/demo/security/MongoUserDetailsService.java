package com.example.demo.security;

import com.example.demo.user.TodoUserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MongoUserDetailsService implements UserDetailsService {

    private final TodoUserRepo repository;

    public MongoUserDetailsService(TodoUserRepo repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .map(user -> new User(user.getUsername(), user.getPassword(), List.of()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
