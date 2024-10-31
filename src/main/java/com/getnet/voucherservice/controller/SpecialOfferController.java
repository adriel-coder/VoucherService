package com.getnet.voucherservice.controller;

import com.getnet.voucherservice.model.SpecialOffer;
import com.getnet.voucherservice.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/special-offers")
public class SpecialOfferController {

    @Autowired
    private SpecialOfferService specialOfferService;

    @PostMapping
    public ResponseEntity<SpecialOffer> createSpecialOffer(@RequestBody SpecialOffer specialOffer) {
        SpecialOffer createdOffer = specialOfferService.saveSpecialOffer(specialOffer);
        return ResponseEntity.ok(createdOffer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSpecialOfferById(@PathVariable String id) {
        Optional<SpecialOffer> offer = specialOfferService.findSpecialOfferById(id);
        return offer.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<SpecialOffer>> getAllSpecialOffers() {
        Iterable<SpecialOffer> offers = specialOfferService.findAllSpecialOffers();
        return ResponseEntity.ok(offers);
    }
}

