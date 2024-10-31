package com.getnet.voucherservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getnet.voucherservice.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public abstract class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    protected String getToken() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("password");

        String json = new ObjectMapper().writeValueAsString(loginRequest);
        
        MvcResult result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andReturn();

        return result.getResponse().getContentAsString();
    }
    
    protected MockHttpServletRequestBuilder getAuthorizedRequest(MockHttpServletRequestBuilder requestBuilder) throws Exception {
        String token = getToken();
        return requestBuilder.header("Authorization", "Bearer " + token);
    }
}
