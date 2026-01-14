package com.example.demo.security;

import com.example.demo.user.IUserRepository;
import com.example.demo.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserContext {
    private final IUserRepository userRepository;

    public UserContext(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }



public User GetCurrentUser() {
    var authentication = SecurityContextHolder.getContext()
            .getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
        throw new RuntimeException("Nenhum Usuario Logado");

    }

    String email = authentication.getName();
    return userRepository.findByEmail(email)
            .orElseThrow(()-> new RuntimeException("Usuario do token não encontrado no banco"));
    }
}