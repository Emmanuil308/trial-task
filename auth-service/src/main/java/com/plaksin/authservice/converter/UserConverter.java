package com.plaksin.authservice.converter;

import com.plaksin.authservice.dto.SavedUserDto;
import com.plaksin.authservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public SavedUserDto toSavedUserDtoFromUser(User user) {
        return SavedUserDto.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .creationDate(user.getCreationDate())
                .build();
    }
}
