package com.getnet.voucherservice.repository;

import com.getnet.voucherservice.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testFindByUsername() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole("USER");
        userRepository.save(user);

        User foundUser = userRepository.findByUsername("testuser");
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
    }

    @Test
    void testExistsByUsername() {
        User user = new User();
        user.setUsername("existinguser");
        user.setPassword("password");
        user.setRole("USER");
        userRepository.save(user);

        boolean exists = userRepository.existsByUsername("existinguser");
        assertTrue(exists);

        boolean nonExists = userRepository.existsByUsername("nonexistentuser");
        assertFalse(nonExists);
    }
}
