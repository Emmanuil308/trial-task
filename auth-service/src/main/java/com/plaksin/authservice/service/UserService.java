package com.plaksin.authservice.service;

import com.plaksin.authservice.model.User;

public interface UserService {
    User saveNewUser(User user);
    boolean checkUserEmail(String email);
    User getUserById(Long userId);
}
