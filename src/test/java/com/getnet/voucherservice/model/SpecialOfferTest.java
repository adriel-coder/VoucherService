package com.getnet.voucherservice.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpecialOfferTest {

    @Test
    public void testSpecialOfferProperties() {
        SpecialOffer offer = new SpecialOffer();
        offer.setName("Special Discount");
        offer.setDiscountPercentage(20.0);

        assertEquals("Special Discount", offer.getName());
        assertEquals(20.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testSpecialOfferIdGeneration() {
        SpecialOffer offer = new SpecialOffer();
        offer.setId("54321");

        assertEquals("54321", offer.getId());
    }
}
