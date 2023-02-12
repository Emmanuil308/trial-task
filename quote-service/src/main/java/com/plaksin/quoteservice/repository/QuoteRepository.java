package com.plaksin.quoteservice.repository;

import com.plaksin.quoteservice.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query(value = "select q from Quote q order by random() limit 1")
    Quote findRandomQuot();

    @Query(value = "select q from Quote q join Vote v on q.id = v.quote.id where v.value = " +
            "'POSITIVE' group by q.id order by count(q.id) desc limit 10")
    List<Quote> findTopQuotes();

    @Query(value = "select q from Quote q join Vote v on q.id = v.quote.id where v.value = " +
            "'NEGATIVE' group by q.id order by count(q.id) desc limit 10")
    List<Quote> findWorseQuotes();

    @Query(value = "select q from Quote q left join fetch q.votes where q.id = :quoteId")
    Optional<Quote> findByIdWithVotes(@Param("quoteId") Long quoteId);
}