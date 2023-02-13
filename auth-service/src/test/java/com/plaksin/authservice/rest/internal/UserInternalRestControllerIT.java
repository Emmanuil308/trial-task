package com.plaksin.authservice.rest.internal;

import com.plaksin.authservice.ContextIT;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserInternalRestControllerIT extends ContextIT {

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/scripts/rest/out/adminRestController/add_users.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/scripts/rest/out/adminRestController/users_clear.sql")
    public void getUserById() throws Exception {

        mockMvc.perform(
                get("/api/internal/users/{userId}", 1L)
        )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.userId", Is.is(1)))
                .andExpect(jsonPath("$.email", Is.is("some1@gmail.com")))
                .andExpect(jsonPath("$.creationDate", Is.is(LocalDate.now().toString())));
    }
}
