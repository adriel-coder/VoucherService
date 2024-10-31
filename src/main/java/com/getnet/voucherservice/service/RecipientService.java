package com.getnet.voucherservice.service;

import com.getnet.voucherservice.model.Recipient;
import com.getnet.voucherservice.repository.RecipientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RecipientService {

    private static final Logger logger = LoggerFactory.getLogger(RecipientService.class);

    @Autowired
    private RecipientRepository recipientRepository;

    @Transactional
    public Recipient saveRecipient(Recipient recipient) {
        logger.info("Saving recipient with email: {}", recipient.getEmail());
        return recipientRepository.save(recipient);
    }

    @Transactional(readOnly = true)
    public Optional<Recipient> findRecipientById(String id) {
        logger.info("Finding recipient by id: {}", id);
        return recipientRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Recipient> findRecipientByEmail(String email) {
        logger.info("Finding recipient by email: {}", email);
        return recipientRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Recipient> findAllRecipients() {
        logger.info("Retrieving all recipients");
        return recipientRepository.findAll();
    }

    @Transactional
    public void deleteRecipientById(String id) {
        logger.info("Deleting recipient by id: {}", id);
        recipientRepository.deleteById(id);
    }
}
