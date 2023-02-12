package com.plaksin.authservice.service;

import com.plaksin.authservice.dto.SavedUserDto;
import com.plaksin.authservice.model.User;

public interface UserDtoService {

    SavedUserDto saveNewUser(User user);
}
