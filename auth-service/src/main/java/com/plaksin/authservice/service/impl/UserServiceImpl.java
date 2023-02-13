package com.plaksin.authservice.service.impl;

import com.plaksin.authservice.model.User;
import com.plaksin.authservice.repository.UserRepository;
import com.plaksin.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveNewUser(User user) {
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       user.setCreationDate(LocalDate.now());
        return userRepository.save(user);
    }

    @Override
    public boolean checkUserEmail(String email) {
        return userRepository.checkUserByEmail(email) > 0;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }
}
