package com.getnet.voucherservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.getnet.voucherservice.model.User;
import com.getnet.voucherservice.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

class UserControllerTest extends BaseControllerTest{

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCurrentUser_authenticated() throws Exception {

        MockHttpServletRequestBuilder request = getAuthorizedRequest(get("/api/users/current-user"));

        mockMvc.perform(request)
               .andExpect(status().isOk())
               .andExpect(content().string("Current user: testuser")); // Use o username aqui
    }

    @Test
    void testGetCurrentUser_anonymous() {
        SecurityContextHolder.clearContext();

        ResponseEntity<?> response = userController.getCurrentUser();

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("User not authenticated", response.getBody());
    }

    @Test
    void testRegisterUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole("USER");

        when(userService.registerUser(user.getUsername(), user.getPassword(), user.getRole())).thenReturn(user);

        ResponseEntity<User> response = userController.registerUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService).registerUser(user.getUsername(), user.getPassword(), user.getRole());
    }
}
