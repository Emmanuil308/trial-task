package com.plaksin.authservice.rest.out;

import com.plaksin.authservice.ContextIT;
import com.plaksin.authservice.model.User;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static java.time.LocalDate.now;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminRestOutControllerIT extends ContextIT {

    private final User user = getUserForTest();

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/scripts/rest/out/adminRestController/users_clear.sql")
    public void saveNewUser_isOk_Test() throws Exception {

        mockMvc.perform(
                post("/api/auth/admin/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.data.email", Is.is("some1@gmail.com")))
                .andExpect(jsonPath("$.data.creationDate", Is.is(now().toString())))
                .andExpect(jsonPath("$.code", Is.is(200)));
    }


    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/scripts/rest/out/adminRestController/add_users.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/scripts/rest/out/adminRestController/users_clear.sql")
    public void saveNewUser_badRequest_test() throws Exception {

        mockMvc.perform(
                post("/api/auth/admin/users")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest())

                .andExpect(jsonPath("$.success", Is.is(false)))
                .andExpect(jsonPath("$.data", IsNull.nullValue()))
                .andExpect(jsonPath("$.code", Is.is(450)))
                .andExpect(jsonPath("$.text", Is.is("User with email some1@gmail.com exist in data base")));
    }

    private User getUserForTest() {
        User user =  new User();
        user.setEmail("some1@gmail.com");
        user.setPassword("1");
        user.setCreationDate(now());
        return user;
    }
}
