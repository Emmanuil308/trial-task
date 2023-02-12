package com.plaksin.quoteservice.converter;

import com.plaksin.quoteservice.dto.QuoteDto;
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
}
