package com.getnet.voucherservice.repository;

import com.getnet.voucherservice.model.SpecialOffer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpecialOfferRepository extends MongoRepository<SpecialOffer, String> {

}