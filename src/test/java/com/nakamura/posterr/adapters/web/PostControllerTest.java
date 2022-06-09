package com.nakamura.posterr.adapters.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nakamura.posterr.TestMocks;
import com.nakamura.posterr.adapters.web.dto.CreatePostInput;
import com.nakamura.posterr.application.AllPostService;
import com.nakamura.posterr.application.domain.Post;
import com.nakamura.posterr.application.exception.LimitRangePostDayException;
import com.nakamura.posterr.application.ports.out.PostPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostPort postPortMock;

    @Autowired
    private AllPostService service;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("GIVEN an CreatePostInput WHEN the input is valid then create a post")
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

    @DisplayName("GIVEN an CreatePostInput WHEN the input is valid then create a post above the limit range post day")
    @Test
    void errorLimitRange() throws Exception {

        willThrow(new LimitRangePostDayException()).given(postPortMock).createPost(any(Post.class));

        final var body = objectMapper.writeValueAsString(new CreatePostInput("TEST", null));

        mockMvc.perform(post("/v1/post")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON_VALUE)
                .content(body))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @DisplayName("GIVEN a page number and userId WHEN User request posts from wherever user THEN retrieve a list of post WITH sucess")
    @Test
    void getAllPostWithSucess() throws Exception {
        final var offset = 5;
        final var size = 5;
        final var userId = 1L;
        final var posts = TestMocks.postMock();
        final Long lastPostId = 100L;
        final Long userProfileId = 0L;

        given(postPortMock.getAllPost(userId, offset, size, lastPostId, userProfileId)).willReturn(List.of(posts));

        mockMvc.perform(get("/v1/post?page=1&size=5&lastPostIdSeen=100")
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].post", is("TEST")))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].dateCreated", is(String.valueOf(OffsetDateTime.MAX))));
    }

    @DisplayName("GIVEN a page number and userId WHEN User request posts from user followed THEN retrieve a list of post WITH sucess")
    @Test
    void getAllPostFromUserFollowedWithSucess() throws Exception {
        final var offset = 5;
        final var size = 5;
        final var userId = 1L;
        final var posts = TestMocks.postMock();
        final Long lastPostId = 100L;

        given(postPortMock.getAllPostFromUserFollowed(userId, offset, size, lastPostId)).willReturn(List.of(posts));

        mockMvc.perform(get("/v1/post?page=1&size=5&seeingAll=false&lastPostIdSeen=100")
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].post", is("TEST")))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].dateCreated", is(String.valueOf(OffsetDateTime.MAX))));
    }

    @DisplayName("GIVEN a page number and userId WHEN User request posts from user THEN retrieve a list of post WITH sucess")
    @Test
    void getAllPostFromUser() throws Exception {
        final var offset = 5;
        final var size = 5;
        final var userId = 1L;
        final var userProfileId = 2L;
        final var posts = TestMocks.postMock();
        final Long lastPostId = 100L;

        given(postPortMock.getAllPost(userId, offset, size, lastPostId, userProfileId)).willReturn(List.of(posts));

        mockMvc.perform(get("/v1/post?page=1&size=5&lastPostIdSeen=100&userProfileId=2")
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].post", is("TEST")))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].dateCreated", is(String.valueOf(OffsetDateTime.MAX))));
    }


}