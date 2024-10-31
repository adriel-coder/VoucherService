package com.getnet.voucherservice.repository;

import com.getnet.voucherservice.model.Recipient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class RecipientRepositoryTest {

    @Autowired
    private RecipientRepository recipientRepository;

    @Test
    public void testFindByEmail() {
        Recipient recipient = new Recipient();
        recipient.setName("John Doe");
        recipient.setEmail("john.doe@example.com");
        recipientRepository.save(recipient);

        Optional<Recipient> foundRecipient = recipientRepository.findByEmail("john.doe@example.com");
        assertTrue(foundRecipient.isPresent());
        assertEquals("John Doe", foundRecipient.get().getName());
    }
}
