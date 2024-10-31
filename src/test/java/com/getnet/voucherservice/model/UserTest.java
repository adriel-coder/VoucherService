package com.getnet.voucherservice.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserModel() {
        User user = new User();
        
        user.setId("1");
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole("USER");
        
        assertEquals("1", user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("USER", user.getRole());
    }
}
