package com.getnet.voucherservice.controller;

import com.getnet.voucherservice.model.SpecialOffer;
import com.getnet.voucherservice.service.SpecialOfferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Optional;

@WebMvcTest(SpecialOfferController.class)
public class SpecialOfferControllerTest extends BaseControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpecialOfferService specialOfferService;

    @MockBean
    private UserDetailsService userDetailsService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void testCreateSpecialOffer() throws Exception {
        SpecialOffer specialOffer = new SpecialOffer();
        specialOffer.setName("Holiday Discount");
        specialOffer.setDiscountPercentage(20.0);

        when(specialOfferService.saveSpecialOffer(any(SpecialOffer.class))).thenReturn(specialOffer);

        mockMvc.perform(post("/api/special-offers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Holiday Discount\", \"discountPercentage\": 20.0}")
                .header("Authorization", "Bearer " + getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Holiday Discount"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountPercentage").value(20.0));
    }

    @Test
    void testGetSpecialOfferById() throws Exception {
        SpecialOffer specialOffer = new SpecialOffer();
        specialOffer.setId("OFFERID");
        specialOffer.setName("Holiday Sale");
        specialOffer.setDiscountPercentage(25.0);

        when(specialOfferService.findSpecialOfferById("OFFERID")).thenReturn(Optional.of(specialOffer));

        mockMvc.perform(get("/api/special-offers/OFFERID")
                .header("Authorization", "Bearer " + getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Holiday Sale"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountPercentage").value(25.0));
    }

}
