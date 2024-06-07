package com.example.crud.controller;

import com.example.crud.model.User;
import com.example.crud.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetAllUsers() throws Exception {
        // Given
        User user1 = new User(1L, "John");
        User user2 = new User(2L, "Jane");
        List<User> users = Arrays.asList(user1, user2);
        when(userService.getAllUsers()).thenReturn(users);

        // When and Then
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane"));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetUserById() throws Exception {
        // Given
        User user = new User(1L, "John");
        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        // When and Then
        mockMvc.perform(get("/api/v1/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"));

        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void testCreateUser() throws Exception {
        // Given
        User user = new User(null, "John");
        User savedUser = new User(1L, "John");
        when(userService.createUser(user)).thenReturn(savedUser);
        String userJson = "{\"name\":\"John\"}";

        // When and Then
        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"));

        verify(userService, times(1)).createUser(user);
    }

    @Test
    void testUpdateUser() throws Exception {
        // Given
        User user = new User(1L, "John");
        User updatedUser = new User(1L, "Jane");
        when(userService.updateUser(user.getId(), updatedUser)).thenReturn(updatedUser);
        String userJson = "{\"id\":1,\"name\":\"Jane\"}";

        // When and Then
        mockMvc.perform(put("/api/v1/users/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Jane"));

        verify(userService, times(1)).updateUser(user.getId(), updatedUser);
    }

    @Test
    void testDeleteUser() throws Exception {
        // Given
        doNothing().when(userService).deleteUser(1L);

        // When and Then
        mockMvc.perform(delete("/api/v1/users/{id}", 1))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(1L);
    }
}
