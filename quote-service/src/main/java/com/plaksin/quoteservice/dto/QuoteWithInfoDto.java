package com.plaksin.quoteservice.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record QuoteWithInfoDto(Long quoteId,
                               String content,
                               LocalDateTime lastModifiedTime,
                               Long sumPositiveVote,
                               Long sumNegativeVote,
                               AuthorDto author) {
}
