package com.plaksin.quoteservice.converter;

import com.plaksin.quoteservice.dto.AuthorDto;
import com.plaksin.quoteservice.dto.SavedUserDto;
import org.springframework.stereotype.Component;

@Component
public class AuthorDtoConverter {

    public AuthorDto toAuthorDtoFromUserDto(SavedUserDto userDto) {
        return AuthorDto.builder()
                .id(userDto.userId())
                .email(userDto.email())
                .build();
    }
}
