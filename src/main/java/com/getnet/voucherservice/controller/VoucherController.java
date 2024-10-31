package com.getnet.voucherservice.controller;

import com.getnet.voucherservice.model.Voucher;
import com.getnet.voucherservice.service.VoucherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/vouchers")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @PostMapping
    public ResponseEntity<Voucher> createVoucher(@RequestBody Voucher voucher) {
        Voucher createdVoucher = voucherService.saveVoucher(voucher);
        return ResponseEntity.ok(createdVoucher);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVoucherById(@PathVariable String id) {
        Optional<Voucher> voucher = voucherService.findVoucherById(id);
        return voucher.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<Voucher>> getAllVouchers() {
        Iterable<Voucher> vouchers = voucherService.findAllVouchers();
        return ResponseEntity.ok(vouchers);
    }

    @GetMapping("/by-code/{voucherCode}")
    public ResponseEntity<Iterable<Voucher>> getVoucherByVoucherCode(@PathVariable String voucherCode) {
    	Iterable<Voucher> vouchers = voucherService.findVoucherByCode(voucherCode);
        return ResponseEntity.ok(vouchers);
    }

    @GetMapping("/by-unique-code/{uniqueCode}")
    public ResponseEntity<Voucher> getVoucherByUniqueCode(@PathVariable String uniqueCode) {
    	Optional<Voucher> voucher = voucherService.findVoucherByUniqueCode(uniqueCode);
        return voucher.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/redeem/{id}")
    public ResponseEntity<Voucher> redeemVoucherById(@PathVariable String id) {
        Optional<Voucher> voucher = voucherService.redeemVoucherById(id);
        return voucher.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
