package com.plaksin.quoteservice.service.impl;


import com.plaksin.quoteservice.exception.EntityNotFoundException;
import com.plaksin.quoteservice.model.Quote;
import com.plaksin.quoteservice.repository.QuoteRepository;
import com.plaksin.quoteservice.service.QuoteService;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    public final QuoteRepository quoteRepository;


    @Override
    public Quote saveOrUpdateQuote(Quote quote) {
        quote.setCreatOrUpdateTime(LocalDateTime.now().withNano(0));
            return quoteRepository.save(quote);
    }

    @Override
    public Optional<Quote> getQuoteById(Long quoteId) {
        return quoteRepository.findByIdWithVotes(quoteId);
    }

    @Override
    public Quote getRandomQuote() {
        return quoteRepository.findRandomQuot();
    }

    @Override
    public void deleteQuoteById(Long quoteId) {
        try {
            quoteRepository.deleteById(quoteId);
        } catch(EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Quote not exist in database");
        }
    }

    @Override
    public List<Quote> getTopQuotes() {
        return quoteRepository.findTopQuotes();
    }

    @Override
    public List<Quote> getWorseQuotes() {
        return quoteRepository.findWorseQuotes();
    }
}
