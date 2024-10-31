package com.getnet.voucherservice.service;

import com.getnet.voucherservice.model.Voucher;
import com.getnet.voucherservice.repository.RecipientRepository;
import com.getnet.voucherservice.repository.SpecialOfferRepository;
import com.getnet.voucherservice.repository.VoucherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VoucherService {

    private static final Logger logger = LoggerFactory.getLogger(VoucherService.class);

    @Autowired
    private VoucherRepository voucherRepository;
    
    @Autowired
    private RecipientRepository recipientRepository; 

    @Autowired
    private SpecialOfferRepository specialOfferRepository; 


    @Transactional
    public Voucher saveVoucher(Voucher voucher) {
    	if (!recipientRepository.existsById(voucher.getRecipientId())) {
            throw new IllegalArgumentException("Recipient with ID " + voucher.getRecipientId() + " does not exist.");
        }

        if (!specialOfferRepository.existsById(voucher.getSpecialOfferId())) {
            throw new IllegalArgumentException("SpecialOffer with ID " + voucher.getSpecialOfferId() + " does not exist.");
        }
        
        if (voucherRepository.existsByUniqueCode(voucher.getUniqueCode())) {
            throw new IllegalArgumentException("Unique code must be unique.");
        }
    	
    	logger.info("Saving voucher with code: {}", voucher.getVoucherCode());
        return voucherRepository.save(voucher);
    }

    @Transactional(readOnly = true)
    public Optional<Voucher> findVoucherById(String id) {
        logger.info("Finding voucher by id: {}", id);
        return voucherRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Voucher> findAllVouchers() {
        logger.info("Retrieving all vouchers");
        return voucherRepository.findAll();
    }

    @Transactional
    public void deleteVoucherById(String id) {
        logger.info("Deleting voucher by id: {}", id);
        voucherRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Voucher> findVoucherByUniqueCode(String uniqueCode) {
        logger.info("Finding voucher by unique code: {}", uniqueCode);
        return voucherRepository.findByUniqueCode(uniqueCode);
    }

	public List<Voucher> findVoucherByCode(String voucherCode) {
		logger.info("Finding voucher by code: {}", voucherCode);
        return voucherRepository.findVoucherByCode(voucherCode);
	}

	public Optional<Voucher> redeemVoucherById(String id) {
		logger.info("Redeeming voucher with id: {}", id);
        Optional<Voucher> optionalVoucher = voucherRepository.findById(id);
        
        if (optionalVoucher.isPresent()) {
            Voucher voucher = optionalVoucher.get();
            if (!voucher.isRedeemed()) {
                voucher.setRedeemed(true);
                voucher.setUsedDate(LocalDate.now());
                voucherRepository.save(voucher);
                logger.info("Voucher with id: {} has been redeemed", id);
                return Optional.of(voucher);
            } else {
                logger.warn("Voucher with id: {} has already been redeemed", id);
            }
        }
        return Optional.empty();
	}
}
