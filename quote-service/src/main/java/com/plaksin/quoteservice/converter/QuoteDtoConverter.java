package com.plaksin.quoteservice.converter;

import com.plaksin.quoteservice.dto.AuthorDto;
import com.plaksin.quoteservice.dto.QuoteDto;
import com.plaksin.quoteservice.dto.QuoteWithInfoDto;
import com.plaksin.quoteservice.model.Quote;
import org.springframework.stereotype.Component;

@Component
public class QuoteDtoConverter {

    public QuoteDto toQuoteDtoFromEntity(Quote quote) {
        return QuoteDto.builder()
                .quoteId(quote.getId())
                .content(quote.getContent())
                .userId(quote.getUserId())
                .time(quote.getCreatOrUpdateTime())
                .build();
    }

    public QuoteWithInfoDto toQuoteWithInfoDto(Quote quote,
                                                 Long sumPositiveVote,
                                                 Long sumNegativeVote,
                                                 AuthorDto author) {
        return QuoteWithInfoDto.builder()
                .quoteId(quote.getId())
                .content(quote.getContent())
                .lastModifiedTime(quote.getCreatOrUpdateTime())
                .sumNegativeVote(sumNegativeVote)
                .sumPositiveVote(sumPositiveVote)
                .author(author)
                .build();
    }
}
