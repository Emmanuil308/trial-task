package com.plaksin.quoteservice.service.impl;

import com.plaksin.quoteservice.converter.QuoteDtoConverter;
import com.plaksin.quoteservice.dto.QuoteDto;
import com.plaksin.quoteservice.model.Quote;
import com.plaksin.quoteservice.service.QuoteDtoService;
import com.plaksin.quoteservice.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuoteDtoServiceImpl implements QuoteDtoService {

    private final QuoteService quoteService;
    private final QuoteDtoConverter dtoConverter;

    @Override
    public QuoteDto saveOrUpdateQuote(Quote quote) {
        return dtoConverter.toQuoteDtoFromEntity(quoteService.saveOrUpdateQuote(quote));
    }

    @Override
    public QuoteDto getQuoteById(Quote quote) {
        return dtoConverter.toQuoteDtoFromEntity(quote);
    }

    @Override
    public QuoteDto getRandomQuote() {
        return dtoConverter.toQuoteDtoFromEntity(quoteService.getRandomQuote());
    }

    @Override
    public List<QuoteDto> getTopQuotes() {
        return quoteService.getTopQuotes().stream()
                .map(dtoConverter::toQuoteDtoFromEntity)
                .toList();
    }
}
