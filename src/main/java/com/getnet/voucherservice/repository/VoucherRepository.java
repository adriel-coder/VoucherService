package com.getnet.voucherservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.getnet.voucherservice.model.Voucher;

import java.util.List;
import java.util.Optional;

public interface VoucherRepository extends MongoRepository<Voucher, String> {
	List<Voucher> findVoucherByCode(String voucherCode);
    Optional<Voucher> findByUniqueCode(String uniqueCode);
	boolean existsByUniqueCode(String uniqueCode);
}
