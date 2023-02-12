package com.plaksin.quoteservice.rest.out;

import com.plaksin.quoteservice.dto.QuoteDto;
import com.plaksin.quoteservice.dto.QuoteWithInfoDto;
import com.plaksin.quoteservice.exception.EntityNotFoundException;
import com.plaksin.quoteservice.model.Quote;
import com.plaksin.quoteservice.model.response.Response;
import com.plaksin.quoteservice.service.QuoteDtoService;
import com.plaksin.quoteservice.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quote/admin")
public class AdminRestOutController {

    private final QuoteService quoteService;
    private final QuoteDtoService quoteDtoService;


    @PostMapping("/quotes")
    public Response<QuoteDto> saveNewQuote(@RequestBody Quote quote) {
        return Response.ok(quoteDtoService.saveOrUpdateQuote(quote));
    }

    @PutMapping("/quotes")
    public Response<QuoteDto> updateQuote(@RequestBody Quote quote) {
        return Response.ok(quoteDtoService.saveOrUpdateQuote(quote));
    }

    @GetMapping("/quotes/{quoteId}")
    public Response<QuoteWithInfoDto> getQuoteByIdWithInfo(@PathVariable("quoteId") Long quoteId) {
        Quote quote = quoteService.getQuoteById(quoteId)
                .orElseThrow(() -> new EntityNotFoundException("Quote not found"));
        return Response.ok(quoteDtoService.getQuoteByIdWithInfo(quote));
    }

    @DeleteMapping("/quotes/{quoteId}")
    public Response<?> deleteQuoteById(@PathVariable("quoteId") Long quoteId) {
        quoteService.deleteQuoteById(quoteId);
        return Response.ok();
    }

    @GetMapping("/quotes/random")
    public Response<QuoteDto> getRandomQuote() {
        return Response.ok(quoteDtoService.getRandomQuote());
    }

    @GetMapping("/quotes/top")
    public Response<List<QuoteDto>> getTopQuotes() {
        return Response.ok(quoteDtoService.getTopQuotes());
    }

    @GetMapping("/quotes/worse")
    public Response<List<QuoteDto>> getWorseQuotes() {
        return Response.ok(quoteDtoService.getWorseQuotes());
    }

}
