package com.getnet.voucherservice.service;

import com.getnet.voucherservice.model.SpecialOffer;
import com.getnet.voucherservice.repository.SpecialOfferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialOfferService {

	private static final Logger logger = LoggerFactory.getLogger(SpecialOfferService.class);

    @Autowired
    private SpecialOfferRepository specialOfferRepository;

    @Transactional
    public SpecialOffer saveSpecialOffer(SpecialOffer specialOffer) {
        logger.info("Saving special offer with discount: {}", specialOffer.getDiscountPercentage());
        return specialOfferRepository.save(specialOffer);
    }

    @Transactional(readOnly = true)
    public Optional<SpecialOffer> findSpecialOfferById(String id) {
        logger.info("Finding special offer by id: {}", id);
        return specialOfferRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<SpecialOffer> findAllSpecialOffers() {
        logger.info("Retrieving all special offers");
        return specialOfferRepository.findAll();
    }

    @Transactional
    public void deleteSpecialOfferById(String id) {
        logger.info("Deleting special offer by id: {}", id);
        specialOfferRepository.deleteById(id);
    }
}
