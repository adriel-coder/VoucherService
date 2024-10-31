package com.getnet.voucherservice.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RecipientTest {

    @Test
    public void testRecipientProperties() {
        Recipient recipient = new Recipient();
        recipient.setName("John Doe");
        recipient.setEmail("john.doe@example.com");

        assertEquals("John Doe", recipient.getName());
        assertEquals("john.doe@example.com", recipient.getEmail());
    }

    @Test
    public void testRecipientIdGeneration() {
        Recipient recipient = new Recipient();
        recipient.setId("12345");

        assertEquals("12345", recipient.getId());
    }
}
