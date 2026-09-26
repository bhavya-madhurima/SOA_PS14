package com.klu.service;

import com.klu.entity.User;

public interface AuthService {

    User register(User user);

    String login(String email, String password);
}