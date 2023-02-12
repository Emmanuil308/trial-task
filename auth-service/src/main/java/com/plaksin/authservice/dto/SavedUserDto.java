package com.plaksin.authservice.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record SavedUserDto(Long userId,
                           String email,
                           LocalDate creationDate) {
}
