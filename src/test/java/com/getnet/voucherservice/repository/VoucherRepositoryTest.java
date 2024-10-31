package com.getnet.voucherservice.repository;

import com.getnet.voucherservice.model.Voucher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class VoucherRepositoryTest {

    @Autowired
    private VoucherRepository voucherRepository;

    @Test
    public void testSaveAndFindById() {
        Voucher voucher = new Voucher();
        voucher.setVoucherCode("VOUCHER2024");
        voucher.setUniqueCode("UNIQUE123");
        Voucher savedVoucher = voucherRepository.save(voucher);

        Optional<Voucher> foundVoucher = voucherRepository.findById(savedVoucher.getId());
        assertTrue(foundVoucher.isPresent());
        assertEquals("VOUCHER2024", foundVoucher.get().getVoucherCode());
        assertEquals("UNIQUE123", foundVoucher.get().getUniqueCode());
    }

    @Test
    public void testFindByUniqueCode() {
        Voucher voucher = new Voucher();
        voucher.setVoucherCode("VOUCHER2024");
        voucher.setUniqueCode("UNIQUE123");
        voucherRepository.save(voucher);

        Optional<Voucher> foundVoucher = voucherRepository.findByUniqueCode("UNIQUE123");
        assertTrue(foundVoucher.isPresent());
        assertEquals("UNIQUE123", foundVoucher.get().getUniqueCode());
    }
}
