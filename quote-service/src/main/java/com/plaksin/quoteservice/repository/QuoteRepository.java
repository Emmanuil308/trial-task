package com.plaksin.quoteservice.repository;

import com.plaksin.quoteservice.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query(value = "select q.id from Quote q")
    List<Long> findAllId();

//    @Query(value = "select q from Quote q left join Vote v on q.id = v.quote.id order by count()")
    List<Quote> findTopQuotes();
}
