package com.getnet.voucherservice.service;

import com.getnet.voucherservice.model.SpecialOffer;
import com.getnet.voucherservice.repository.SpecialOfferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SpecialOfferServiceTest {

    @Mock
    private SpecialOfferRepository specialOfferRepository;

    @InjectMocks
    private SpecialOfferService specialOfferService;

    @Test
    public void testCreateSpecialOffer() {
        SpecialOffer offer = new SpecialOffer();
        offer.setName("Special Discount");
        when(specialOfferRepository.save(offer)).thenReturn(offer);

        SpecialOffer createdOffer = specialOfferService.saveSpecialOffer(offer);
        assertNotNull(createdOffer);
        assertEquals("Special Discount", createdOffer.getName());
    }

    @Test
    public void testFindSpecialOfferById() {
        SpecialOffer offer = new SpecialOffer();
        offer.setId("offerId");
        when(specialOfferRepository.findById("offerId")).thenReturn(Optional.of(offer));

        Optional<SpecialOffer> foundOffer = specialOfferService.findSpecialOfferById("offerId");
        assertTrue(foundOffer.isPresent());
        assertEquals("offerId", foundOffer.get().getId());
    }
}
