package com.plaksin.authservice.service.impl;

import com.plaksin.authservice.converter.UserConverter;
import com.plaksin.authservice.dto.SavedUserDto;
import com.plaksin.authservice.model.User;
import com.plaksin.authservice.service.UserDtoService;
import com.plaksin.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDtoServiceImpl implements UserDtoService {

    private final UserService userService;
    private final UserConverter userConverter;

    @Override
    public SavedUserDto saveNewUser(User user) {
        return userConverter.toSavedUserDtoFromUser(userService.saveNewUser(user));
    }

    @Override
    public SavedUserDto getUserById(Long userId) {
        return userConverter.toSavedUserDtoFromUser(userService.getUserById(userId));
    }
}
