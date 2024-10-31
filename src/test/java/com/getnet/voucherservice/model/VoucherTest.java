package com.getnet.voucherservice.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class VoucherTest {

    @Test
    public void testVoucherProperties() {
        Voucher voucher = new Voucher();
        voucher.setVoucherCode("VOUCHER2024");
        voucher.setUniqueCode("UNIQUE123");
        voucher.setExpiryDate(LocalDate.now().plusDays(30));

        assertEquals("VOUCHER2024", voucher.getVoucherCode());
        assertEquals("UNIQUE123", voucher.getUniqueCode());
        assertNotNull(voucher.getExpiryDate());
    }

    @Test
    public void testUsedDate() {
        Voucher voucher = new Voucher();
        LocalDate usedDate = LocalDate.now();
        voucher.setUsedDate(usedDate);

        assertEquals(usedDate, voucher.getUsedDate());
    }
}
