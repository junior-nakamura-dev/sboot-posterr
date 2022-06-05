package com.nakamura.posterr.adapters.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nakamura.posterr.TestMocks;
import com.nakamura.posterr.adapters.web.dto.FollowUserInput;
import com.nakamura.posterr.application.UserService;
import com.nakamura.posterr.application.ports.out.UserPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserPort userPortMock;

    @Autowired
    private UserService service;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Given an userId when userId has following user then return them")
    @Test
    void getAllFollowingUserWithSucess() throws Exception {

        given(userPortMock.getFollowingUserById(1L)).willReturn(List.of(TestMocks.followingEntityMock()));

        mockMvc.perform(get("/v1/user/following").accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].userFollowingId", is(2)));
    }

    @DisplayName("Given an userId when userId hasn´t following user then return empty list")
    @Test
    void getAllFollowingUserEmptyList() throws Exception {

        given(userPortMock.getFollowingUserById(1L)).willReturn(List.of());

        mockMvc.perform(get("/v1/user/following").accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @DisplayName("Given an userId when userId has followed user then return them")
    @Test
    void getAllFollowedUserWithSucess() throws Exception {

        given(userPortMock.getFollowedUserById(1L)).willReturn(List.of(TestMocks.followedEntityMock()));

        mockMvc.perform(get("/v1/user/followed").accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].userFollowedId", is(2)));
    }

    @DisplayName("Given an userId when userId hasn´t followed user then return empty list")
    @Test
    void getAllFollowedUserEmptyList() throws Exception {

        given(userPortMock.getFollowedUserById(1L)).willReturn(List.of());

        mockMvc.perform(get("/v1/user/followed").accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @DisplayName("Given an userFollowingId when userId wants follow him then return following him")
    @Test
    void followUserSuccess() throws Exception {

        willDoNothing().given(userPortMock).followUser(TestMocks.followingUserMock());

        final var body = objectMapper.writeValueAsString(new FollowUserInput(2L));

        mockMvc.perform(post("/v1/user/follow")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON_VALUE)
                .content(body))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @DisplayName("Given an userFollowingId when userId wants follow himself then return error")
    @Test
    void followYouserlfUserError() throws Exception {
        final var body = objectMapper.writeValueAsString(new FollowUserInput(1L));

        mockMvc.perform(post("/v1/user/follow")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON_VALUE)
                .content(body))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }


}