package com.plaksin.quoteservice.service;

import com.plaksin.quoteservice.model.Quote;

import java.util.List;
import java.util.Optional;

public interface QuoteService {

    Quote saveOrUpdateQuote(Quote quote);
    Optional<Quote> getQuoteById(Long quoteId);
    Quote getRandomQuote();
    void deleteQuoteById(Long quoteId);
    List<Quote> getTopQuotes();
    List<Quote> getWorseQuotes();
}
