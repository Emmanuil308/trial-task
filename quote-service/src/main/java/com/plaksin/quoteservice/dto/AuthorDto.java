package com.plaksin.quoteservice.dto;

import lombok.Builder;

@Builder
public record AuthorDto(Long id,
                        String email) {
}
