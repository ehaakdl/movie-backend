package com.mose.movie.controller;

import com.mose.movie.etc.define.ErrorResponseException;
import com.mose.movie.component.utils.CommonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.mose.movie.controller.ErrorControllerTest.TestController.TEST_URL;
import static com.mose.movie.etc.define.eResponseErrorInfo.DEFAULT;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ErrorControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new TestController())
                .setControllerAdvice(new ErrorController(new CommonUtils()))
                .build();
    }

    @Test
    void errorResponseException() throws Exception {
        mockMvc
                .perform(post(TEST_URL).param("param", String.valueOf(1)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", equalTo(false)))
                .andExpect(jsonPath("$.message", equalTo(DEFAULT.getMessage())));
        mockMvc
                .perform(post(TEST_URL).param("param", String.valueOf(2)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", equalTo(false)))
                .andExpect(jsonPath("$.message", equalTo(DEFAULT.getMessage())));
        mockMvc
                .perform(post(TEST_URL).param("param", String.valueOf(3)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", equalTo(false)))
                .andExpect(jsonPath("$.message", equalTo(null)));
        mockMvc
                .perform(post(TEST_URL).param("param", String.valueOf(4)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", equalTo(false)))
                .andExpect(jsonPath("$.message", equalTo(DEFAULT.getMessage())));

    }

    @RestController
    static class TestController {
        public static final String TEST_URL = "/test";

        @PostMapping(TEST_URL)
        public void test(@RequestParam(required = false) int param) throws Exception {
            switch (param) {
                case 1 -> throw new ErrorResponseException(new NullPointerException("원인"), DEFAULT, false);
                case 2 -> throw new ErrorResponseException(DEFAULT, false);
                case 3 -> throw new ErrorResponseException(null, false);
                default -> throw new Exception();
            }
        }
    }
}