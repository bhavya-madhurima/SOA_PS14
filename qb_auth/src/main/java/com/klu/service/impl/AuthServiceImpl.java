package com.klu.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.klu.entity.User;
import com.klu.repo.UserRepo;
import com.klu.service.AuthService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    private final String SECRET =
            "qb-auth-secret-key-for-jwt-authentication-123456789";

    public AuthServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User register(User user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("USER");
        }

        return userRepo.save(user);
    }

    @Override
    public String login(String email, String password) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() ->
                    new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(
                password,
                user.getPassword())) {

            throw new RuntimeException("Invalid email or password");
        }

        Key key = Keys.hmacShaKeyFor(
                SECRET.getBytes(StandardCharsets.UTF_8)
        );

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole())
                .issuedAt(new Date())
                .expiration(
                    new Date(System.currentTimeMillis()
                    + 1000 * 60 * 60)
                )
                .signWith(key)
                .compact();
    }
}