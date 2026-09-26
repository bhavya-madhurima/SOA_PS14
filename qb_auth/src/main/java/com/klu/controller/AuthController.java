package com.klu.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.klu.entity.User;
import com.klu.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        return authService.register(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(
            @RequestBody User user) {

        String token = authService.login(
                user.getEmail(),
                user.getPassword()
        );

        return Map.of(
                "token", token,
                "type", "Bearer"
        );
    }
}
