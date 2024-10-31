package com.getnet.voucherservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.getnet.voucherservice.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
	boolean existsByUsername(String username);
}
