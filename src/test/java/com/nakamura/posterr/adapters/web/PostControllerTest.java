package com.nakamura.posterr.adapters.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nakamura.posterr.TestMocks;
import com.nakamura.posterr.adapters.web.dto.CreatePostInput;
import com.nakamura.posterr.application.PostService;
import com.nakamura.posterr.application.exception.LimitRangePostDayException;
import com.nakamura.posterr.application.ports.out.PostPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostPort postPortMock;

    @Autowired
    private PostService service;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Given an CreatePostInput when the input is valid then create a post")
    @Test
    void createPostWithSuccess() throws Exception {

        willDoNothing().given(postPortMock).createPost(TestMocks.postMock());

        final var body = objectMapper.writeValueAsString(new CreatePostInput("TEST", null));

        mockMvc.perform(post("/v1/post")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON_VALUE)
                .content(body))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @DisplayName("Given an CreatePostInput when the input is valid then create a post above the limit range post day")
    @Test
    void errorLimitRange() throws Exception {

        willThrow(new LimitRangePostDayException()).given(postPortMock).createPost(TestMocks.postMock());

        final var body = objectMapper.writeValueAsString(new CreatePostInput("TEST", null));

        mockMvc.perform(post("/v1/post")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON_VALUE)
                .content(body))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

}