package com.nakamura.posterr.adapters.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nakamura.posterr.TestMocks;
import com.nakamura.posterr.adapters.repository.entity.FollowingEntity;
import com.nakamura.posterr.adapters.web.dto.FollowUserInput;
import com.nakamura.posterr.application.UserService;
import com.nakamura.posterr.application.exception.AlreadyFollowThisUserException;
import com.nakamura.posterr.application.exception.AlreadyUnfollowThisUserException;
import com.nakamura.posterr.application.ports.out.PostPort;
import com.nakamura.posterr.application.ports.out.UserPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @MockBean
    private PostPort postPortMock;

    @Autowired
    private UserService service;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Given an userId when userId has following user then return them")
    @Test
    void getAllFollowingUserWithSucess() throws Exception {

        given(userPortMock.getFollowingUserById(1L)).willReturn(List.of(TestMocks.followingEntityMock()));

        mockMvc.perform(get("/v1/user/1/following").accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", is(1)))
                .andExpect(jsonPath("$.followingUsersList[0].userId", is(1)))
                .andExpect(jsonPath("$.followingUsersList[0].userFollowingId", is(2)));
    }

    @DisplayName("Given an userId when userId hasn´t following user then return empty list")
    @Test
    void getAllFollowingUserEmptyList() throws Exception {

        given(userPortMock.getFollowingUserById(1L)).willReturn(List.of());

        mockMvc.perform(get("/v1/user/1/following").accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", is(0)))
                .andExpect(jsonPath("$.followingUsersList", hasSize(0)));
    }

    @DisplayName("Given an userId when userId has followed user then return them")
    @Test
    void getAllFollowedUserWithSucess() throws Exception {

        given(userPortMock.getFollowedUserById(1L)).willReturn(List.of(TestMocks.followedEntityMock()));

        mockMvc.perform(get("/v1/user/1/followed").accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", is(1)))
                .andExpect(jsonPath("$.followedUserList[0].userId", is(1)))
                .andExpect(jsonPath("$.followedUserList.[0].userFollowedId", is(2)));
    }

    @DisplayName("Given an userId when return user profile")
    @Test
    void getUserProfile() throws Exception {
        final var userId = 1L;
        final var userProfileId = 2L;
        final var userEntity = TestMocks.userEntityMock(userProfileId);

        given(postPortMock.countPosts(userProfileId)).willReturn(5L);
        given(userPortMock.getUserProfile(userProfileId)).willReturn(userEntity);
        given(userPortMock.isFollow(userId, userProfileId)).willReturn(Optional.of(FollowingEntity.builder().build()));

        mockMvc.perform(get("/v1/user/2").accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.username", is("TEST")))
                .andExpect(jsonPath("$.following", is(true)))
                .andExpect(jsonPath("$.amountPosts", is(5)))
                .andExpect(jsonPath("$.dateJoined", is(String.valueOf(OffsetDateTime.MAX))));
    }

    @DisplayName("Given an userId when userId hasn´t followed user then return empty list")
    @Test
    void getAllFollowedUserEmptyList() throws Exception {

        given(userPortMock.getFollowedUserById(1L)).willReturn(List.of());

        mockMvc.perform(get("/v1/user/1/followed").accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", is(0)))
                .andExpect(jsonPath("$.followedUserList", hasSize(0)));
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

    @DisplayName("Given an userFollowingId when userId wants follow an user already followed then return error")
    @Test
    void followUserAlreadyFollowedError() throws Exception {
        willThrow(new AlreadyFollowThisUserException()).given(userPortMock).followUser(TestMocks.followingUserMock());

        final var body = objectMapper.writeValueAsString(new FollowUserInput(2L));

        mockMvc.perform(post("/v1/user/follow")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON_VALUE)
                .content(body))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @DisplayName("Given an userFollowingId when userId wants unfollow him then return success")
    @Test
    void unfollowUserSuccess() throws Exception {

        willDoNothing().given(userPortMock).unfollowUser(TestMocks.followingUserMock());

        mockMvc.perform(delete("/v1/user/unfollow/2")
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @DisplayName("Given an userFollowingId when userId wants unfollow him but already unfollowed then return error")
    @Test
    void unfollowUserAlreadyUnfollowedError() throws Exception {
        willThrow(new AlreadyUnfollowThisUserException()).given(userPortMock).unfollowUser(TestMocks.followingUserMock());

        mockMvc.perform(delete("/v1/user/unfollow/2")
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }


}