package com.mose.movie.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static com.mose.movie.etc.define.Urls.CSRF_TOKEN;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CsrfTokenControllerTest {
    @Autowired
    private MockMvc mockMvc;
    MockHttpSession session;
    private String csrfToken;
    private String parameterName;
    private String headerName;

    @BeforeEach
    void createCsrfToken() throws Exception {
        session = new MockHttpSession();
        MockHttpServletResponse response = mockMvc
                .perform(get(CSRF_TOKEN).session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", is(notNullValue())))
                .andExpect(jsonPath("$.parameterName", is(notNullValue())))
                .andExpect(jsonPath("$.headerName", is(notNullValue())))
                .andReturn()
                .getResponse();
        JsonObject responseJson = JsonParser.parseString(response.getContentAsString()).getAsJsonObject();
        csrfToken = responseJson.get("token").getAsString();
        parameterName = responseJson.get("parameterName").getAsString();
        headerName = responseJson.get("headerName").getAsString();
    }

    @Test
    void requestParamCsrfToken() throws Exception {
        mockMvc
                .perform(post("/test").param(parameterName, csrfToken).session(session))
                .andExpect(status().isOk());
    }

    @Test
    void requestHeaderCsrfToken() throws Exception {
        mockMvc
                .perform(post("/test").header(headerName, csrfToken).session(session))
                .andExpect(status().isOk());
    }
    @Test
    void requestNoCsrfToken() throws Exception {
        mockMvc
                .perform(post("/test").session(session))
                .andExpect(status().isForbidden());
    }
}