package com.getnet.voucherservice.controller;

import com.getnet.voucherservice.model.Recipient;
import com.getnet.voucherservice.service.RecipientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(RecipientController.class)
class RecipientControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RecipientService recipientService;

    @InjectMocks
    private RecipientController recipientController;

    @Test
    void testCreateRecipient() throws Exception {
        Recipient recipient = new Recipient();
        recipient.setName("Jane Doe");
        recipient.setEmail("jane.doe@example.com");

        when(recipientService.saveRecipient(any(Recipient.class))).thenReturn(recipient);

        mockMvc.perform(post("/api/recipients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Jane Doe\", \"email\": \"jane.doe@example.com\"}")
                .header("Authorization", "Bearer " + getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("jane.doe@example.com"));
    }

    @Test
    void testGetRecipientById() throws Exception {
        Recipient recipient = new Recipient();
        recipient.setId("1");
        recipient.setName("Jane Doe");
        recipient.setEmail("jane.doe@example.com");

        when(recipientService.findRecipientById("1")).thenReturn(Optional.of(recipient));

        mockMvc.perform(get("/api/recipients/1")
                .header("Authorization", "Bearer " + getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("jane.doe@example.com"));
    }

    @Test
    void testGetRecipientByEmail() throws Exception {
        Recipient recipient = new Recipient();
        recipient.setName("Jane Doe");
        recipient.setEmail("jane.doe@example.com");

        when(recipientService.findRecipientByEmail("jane.doe@example.com")).thenReturn(Optional.of(recipient));

        mockMvc.perform(get("/api/recipients/email/jane.doe@example.com")
                .header("Authorization", "Bearer " + getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("jane.doe@example.com"));
    }

    @Test
    void testGetAllRecipients() throws Exception {
        List<Recipient> recipients = new ArrayList<>();
        Recipient recipient1 = new Recipient();
        recipient1.setId("1");
        recipient1.setName("Jane Doe");
        recipient1.setEmail("jane.doe@example.com");
        recipients.add(recipient1);

        Recipient recipient2 = new Recipient();
        recipient2.setId("2");
        recipient2.setName("John Doe");
        recipient2.setEmail("john.doe@example.com");
        recipients.add(recipient2);

        when(recipientService.findAllRecipients()).thenReturn(recipients);

        mockMvc.perform(get("/api/recipients")
                .header("Authorization", "Bearer " + getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("jane.doe@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("john.doe@example.com"));
    }
}
