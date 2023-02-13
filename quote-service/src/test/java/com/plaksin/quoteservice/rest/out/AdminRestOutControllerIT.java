package com.plaksin.quoteservice.rest.out;

import com.plaksin.quoteservice.ContextIT;
import com.plaksin.quoteservice.dto.SavedUserDto;
import com.plaksin.quoteservice.feign.AuthFeignClient;
import com.plaksin.quoteservice.model.Quote;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminRestOutControllerIT extends ContextIT {

    @MockBean
    private AuthFeignClient authFeignClient;

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/scripts/rest/out/adminRestController/quote_clear.sql")
    public void saveNewQuote_isOk_test() throws Exception {

        mockMvc.perform(
                        post("/api/quote/admin/quotes")
                                .content(objectMapper.writeValueAsString(newQuote()))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.data.content", Is.is("Test content")))
                .andExpect(jsonPath("$.data.userId", Is.is(7777)))
                .andExpect(jsonPath("$.code", Is.is(200)));
    }


    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/scripts/rest/out/adminRestController/add_quote.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/scripts/rest/out/adminRestController/quote_clear.sql")
    public void updateQuote_IsOk_test() throws Exception {

        Quote oldQuote = entityManager.createQuery("select q from Quote q where q.id = :id", Quote.class)
                .setParameter("id", 5)
                .getSingleResult();

        assertThat(oldQuote.getContent()).isEqualTo("smart quote 2");

        mockMvc.perform(
                        put("/api/quote/admin/quotes")
                                .content(objectMapper.writeValueAsString(updateQuote()))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.data.content", Is.is("Other content for Test")))
                .andExpect(jsonPath("$.data.userId", Is.is(2)))
                .andExpect(jsonPath("$.data.quoteId", Is.is(5)))
                .andExpect(jsonPath("$.code", Is.is(200)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/scripts/rest/out/adminRestController/add_quote.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/scripts/rest/out/adminRestController/quote_clear.sql")
    public void getQuoteByIdWithInfo_isOk_test() throws Exception {

        Mockito.when(authFeignClient.getUserById(1L)).thenReturn(
                SavedUserDto.builder()
                        .userId(9999L)
                        .email("test@mail.com")
                        .creationDate(LocalDate.of(2023,2, 11))
                        .build()
        );

        mockMvc.perform(
                get("/api/quote/admin/quotes/{quoteId}", 6)
        )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.data.quoteId", Is.is(6)))
                .andExpect(jsonPath("$.data.sumPositiveVote", Is.is(0)))
                .andExpect(jsonPath("$.data.sumNegativeVote", Is.is(0)))
                .andExpect(jsonPath("$.data.author.id", Is.is(9999)))
                .andExpect(jsonPath("$.data.author.email", Is.is("test@mail.com")))
                .andExpect(jsonPath("$.data.content", Is.is("another inspirational quote 5")))
                .andExpect(jsonPath("$.code", Is.is(200)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/scripts/rest/out/adminRestController/quote_clear.sql")
    public void getQuoteByIdWithInfo_isBadRequest_test() throws Exception {

        mockMvc.perform(
                get("/api/quote/admin/quotes/{quoteId}", 888888)
        )
                .andExpect(status().isBadRequest())

                .andExpect(jsonPath("$.success", Is.is(false)))
                .andExpect(jsonPath("$.data", IsNull.nullValue()))
                .andExpect(jsonPath("$.code", Is.is(460)))
                .andExpect(jsonPath("$.text", Is.is("Quote not found")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/scripts/rest/out/adminRestController/add_quote.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/scripts/rest/out/adminRestController/quote_clear.sql")
    public void deleteQuoteById_isOk_test() throws Exception {

        Quote oldQuote = entityManager.createQuery("select q from Quote q where q.id = :id", Quote.class)
                .setParameter("id", 4L)
                .getSingleResult();

        assertThat(oldQuote).isNotNull();

        mockMvc.perform(
                        delete("/api/quote/admin/quotes/{quoteId}", 4L)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.data", IsNull.nullValue()))
                .andExpect(jsonPath("$.code", Is.is(200)));

        boolean oldQuoteIsExist = entityManager
                .createQuery("select count(q) from Quote q where q.id = :id", Long.class)
                .setParameter("id", 4)
                .getSingleResult() > 0;

        assertThat(oldQuoteIsExist).isFalse();
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/scripts/rest/out/adminRestController/add_quote.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/scripts/rest/out/adminRestController/quote_clear.sql")
    public void getRandomQuote() throws Exception {

        mockMvc.perform(
                        get("/api/quote/admin/quotes/random")
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.data", IsNull.notNullValue()))
                .andExpect(jsonPath("$.code", Is.is(200)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/scripts/rest/out/adminRestController/add_quote.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/scripts/rest/out/adminRestController/quote_clear.sql")
    public void getTopQuotes() throws Exception {

        mockMvc.perform(
                get("/api/quote/admin/quotes/top")
        )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.code", Is.is(200)))

                .andExpect(jsonPath("$.data.size()", Is.is(10)))

                .andExpect(jsonPath("$.data[0].quoteId", Is.is(1)))
                .andExpect(jsonPath("$.data[0].content", Is.is("inspirational quote 1")))
                .andExpect(jsonPath("$.data[0].userId", Is.is(1)))

                .andExpect(jsonPath("$.data[1].quoteId", Is.is(7)))
                .andExpect(jsonPath("$.data[1].content", Is.is("one more inspirational quote 6")))
                .andExpect(jsonPath("$.data[1].userId", Is.is(1)))

                .andExpect(jsonPath("$.data[2].quoteId", Is.is(3)))
                .andExpect(jsonPath("$.data[2].content", Is.is("inspirational quote 4")))
                .andExpect(jsonPath("$.data[2].userId", Is.is(1)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/scripts/rest/out/adminRestController/add_quote.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/scripts/rest/out/adminRestController/quote_clear.sql")
    public void getWorseQuotes() throws Exception {

        mockMvc.perform(
                        get("/api/quote/admin/quotes/worse")
                )
                .andExpect(status().isOk())
                .andDo(print())

                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.code", Is.is(200)))

                .andExpect(jsonPath("$.data.size()", Is.is(7)))

                .andExpect(jsonPath("$.data[0].quoteId", Is.is(11)))
                .andExpect(jsonPath("$.data[0].content", Is.is("joke 3")))
                .andExpect(jsonPath("$.data[0].userId", Is.is(3)))

                .andExpect(jsonPath("$.data[1].quoteId", Is.is(5)))
                .andExpect(jsonPath("$.data[1].content", Is.is("smart quote 2")))
                .andExpect(jsonPath("$.data[1].userId", Is.is(2)));

    }

    private static Quote newQuote() {
        Quote quote = new Quote();
        quote.setUserId(7777L);
        quote.setContent("Test content");
        return quote;
    }

    private static Quote updateQuote() {
        Quote quote = new Quote();
        quote.setId(5L);
        quote.setUserId(2L);
        quote.setContent("Other content for Test");
        return quote;
    }
}
