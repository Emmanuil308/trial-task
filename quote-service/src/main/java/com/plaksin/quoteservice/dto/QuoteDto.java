package com.plaksin.quoteservice.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record QuoteDto(Long quoteId,
                       String content,
                       Long userId,
                       LocalDateTime time) {
}
