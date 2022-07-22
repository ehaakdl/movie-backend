package com.mose.movie.controller.csrf;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    private String TEST_URL = "/test";

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
    @DisplayName("토큰을 파라미터에 넣었을 때 정상 작동 하는지 체크")
    void requestParamCsrfToken() throws Exception {
        mockMvc
                .perform(post(TEST_URL).param(parameterName, csrfToken).session(session))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("토큰을 헤더에 넣을 떄 정상 작동하는지 체크")
    void requestHeaderCsrfToken() throws Exception {
        mockMvc
                .perform(post(TEST_URL).header(headerName, csrfToken).session(session))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("토큰 헤더에 안넣을 떄")
    void requestNoCsrfToken() throws Exception {
        mockMvc
                .perform(post(TEST_URL).session(session))
                .andExpect(status().isForbidden());
    }
}