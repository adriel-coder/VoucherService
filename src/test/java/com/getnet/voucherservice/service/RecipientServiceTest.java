package com.getnet.voucherservice.service;

import com.getnet.voucherservice.model.Recipient;
import com.getnet.voucherservice.repository.RecipientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RecipientServiceTest {

    @Mock
    private RecipientRepository recipientRepository;

    @InjectMocks
    private RecipientService recipientService;

    @Test
    public void testSaveRecipient() {
        Recipient recipient = new Recipient();
        recipient.setEmail("test@example.com");
        when(recipientRepository.save(recipient)).thenReturn(recipient);

        Recipient savedRecipient = recipientService.saveRecipient(recipient);
        assertNotNull(savedRecipient);
        assertEquals("test@example.com", savedRecipient.getEmail());
    }

    @Test
    public void testFindRecipientById() {
        Recipient recipient = new Recipient();
        recipient.setId("testId");
        when(recipientRepository.findById("testId")).thenReturn(Optional.of(recipient));

        Optional<Recipient> foundRecipient = recipientService.findRecipientById("testId");
        assertTrue(foundRecipient.isPresent());
        assertEquals("testId", foundRecipient.get().getId());
    }

    @Test
    public void testFindRecipientByEmail() {
        Recipient recipient = new Recipient();
        recipient.setEmail("test@example.com");
        when(recipientRepository.findByEmail("test@example.com")).thenReturn(Optional.of(recipient));

        Optional<Recipient> foundRecipient = recipientService.findRecipientByEmail("test@example.com");
        assertTrue(foundRecipient.isPresent());
        assertEquals("test@example.com", foundRecipient.get().getEmail());
    }

    @Test
    public void testDeleteRecipientById() {
        recipientService.deleteRecipientById("testId");
        verify(recipientRepository, times(1)).deleteById("testId");
    }
}
