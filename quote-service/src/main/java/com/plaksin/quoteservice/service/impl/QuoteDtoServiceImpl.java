package com.plaksin.quoteservice.service.impl;

import com.plaksin.quoteservice.converter.AuthorDtoConverter;
import com.plaksin.quoteservice.converter.QuoteDtoConverter;
import com.plaksin.quoteservice.dto.AuthorDto;
import com.plaksin.quoteservice.dto.QuoteDto;
import com.plaksin.quoteservice.dto.QuoteWithInfoDto;
import com.plaksin.quoteservice.enums.VoteValues;
import com.plaksin.quoteservice.feign.AuthFeignClient;
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
    private final AuthFeignClient authFeignClient;
    private final AuthorDtoConverter authorDtoConverter;

    @Override
    public QuoteDto saveOrUpdateQuote(Quote quote) {
        return dtoConverter.toQuoteDtoFromEntity(quoteService.saveOrUpdateQuote(quote));
    }

    @Override
    public QuoteWithInfoDto getQuoteByIdWithInfo(Quote quote) {
        return dtoConverter.toQuoteWithInfoDto(quote,
                getSumPositiveVote(quote),
                getSumNegativeVote(quote),
                getAuthor(quote.getUserId()));
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

    @Override
    public List<QuoteDto> getWorseQuotes() {
        return quoteService.getWorseQuotes().stream()
                .map(dtoConverter::toQuoteDtoFromEntity)
                .toList();
    }

    private Long getSumPositiveVote(Quote quote) {
        return quote.getVotes().stream()
                .filter(vote -> vote.getValue() == VoteValues.POSITIVE)
                .count();
    }

    private Long getSumNegativeVote(Quote quote) {
        return quote.getVotes().stream()
                .filter(vote -> vote.getValue() == VoteValues.NEGATIVE)
                .count();
    }

    private AuthorDto getAuthor(Long userId) {
       return authorDtoConverter.toAuthorDtoFromUserDto(authFeignClient.getUserById(userId));
    }
}
