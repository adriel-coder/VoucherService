package com.getnet.voucherservice.repository;

import com.getnet.voucherservice.model.SpecialOffer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class SpecialOfferRepositoryTest {

    @Autowired
    private SpecialOfferRepository specialOfferRepository;

    @Test
    public void testSaveAndFindById() {
        SpecialOffer offer = new SpecialOffer();
        offer.setName("Winter Discount");
        offer.setDiscountPercentage(15.0);
        SpecialOffer savedOffer = specialOfferRepository.save(offer);

        Optional<SpecialOffer> foundOffer = specialOfferRepository.findById(savedOffer.getId());
        assertTrue(foundOffer.isPresent());
        assertEquals("Winter Discount", foundOffer.get().getName());
        assertEquals(15.0, foundOffer.get().getDiscountPercentage());
    }
}
