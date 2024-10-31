package com.getnet.voucherservice.controller;

import com.getnet.voucherservice.model.Recipient;
import com.getnet.voucherservice.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/recipients")
public class RecipientController {

    @Autowired
    private RecipientService recipientService;

    @PostMapping
    public ResponseEntity<Recipient> createRecipient(@RequestBody Recipient recipient) {
        Recipient createdRecipient = recipientService.saveRecipient(recipient);
        return ResponseEntity.ok(createdRecipient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipientById(@PathVariable String id) {
        Optional<Recipient> recipient = recipientService.findRecipientById(id);
        return recipient.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("email/{email}")
    public ResponseEntity<?> getRecipientByEmail(@PathVariable String email) {
        Optional<Recipient> recipient = recipientService.findRecipientByEmail(email);
        return recipient.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<Recipient>> getAllRecipients() {
    	Iterable<Recipient> recipients = recipientService.findAllRecipients();
        return ResponseEntity.ok(recipients);
    }
}

