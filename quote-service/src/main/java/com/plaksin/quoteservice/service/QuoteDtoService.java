package com.plaksin.quoteservice.service;

import com.plaksin.quoteservice.dto.QuoteDto;
import com.plaksin.quoteservice.dto.QuoteWithInfoDto;
import com.plaksin.quoteservice.model.Quote;

import java.util.List;

public interface QuoteDtoService {

    QuoteDto saveOrUpdateQuote(Quote quote);
    QuoteWithInfoDto getQuoteByIdWithInfo(Quote quote);
    QuoteDto getRandomQuote();
    List<QuoteDto> getTopQuotes();
    List<QuoteDto> getWorseQuotes();
}
