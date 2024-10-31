package com.getnet.voucherservice.service;

import com.getnet.voucherservice.model.Voucher;
import com.getnet.voucherservice.repository.RecipientRepository;
import com.getnet.voucherservice.repository.SpecialOfferRepository;
import com.getnet.voucherservice.repository.VoucherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class VoucherServiceTest {

    @Mock
    private VoucherRepository voucherRepository;
    
    @Mock
    private RecipientRepository recipientRepository;
    
    @Mock
    private SpecialOfferRepository specialOfferRepository;

    @InjectMocks
    private VoucherService voucherService;

    @Test
    public void testCreateVoucher() {
        Voucher voucher = new Voucher();
        voucher.setVoucherCode("VOUCHER2024");
        voucher.setRecipientId("123");
        voucher.setUniqueCode("Unique");
        voucher.setUsedDate(LocalDate.now());
        voucher.setExpiryDate(LocalDate.of(2024, 11, 30));
        voucher.setRedeemed(true);
        voucher.setSpecialOfferId("56");
        when(recipientRepository.existsById("123")).thenReturn(true);
        when(specialOfferRepository.existsById("56")).thenReturn(true);
        when(voucherRepository.save(voucher)).thenReturn(voucher);

        Voucher createdVoucher = voucherService.saveVoucher(voucher);
        assertNotNull(createdVoucher);
        assertEquals("VOUCHER2024", createdVoucher.getVoucherCode());
    }

    @Test
    public void testFindVoucherByCode() {
        Voucher voucher1 = new Voucher();
        voucher1.setVoucherCode("VOUCHER2024");
        
        Voucher voucher2 = new Voucher();
        voucher2.setVoucherCode("VOUCHER2024");

        when(voucherRepository.findVoucherByCode("VOUCHER2024")).thenReturn(Arrays.asList(voucher1, voucher2));

        List<Voucher> foundVouchers = voucherService.findVoucherByCode("VOUCHER2024");

        assertFalse(foundVouchers.isEmpty(), "The list of vouchers should not be empty");
        assertEquals(2, foundVouchers.size(), "There should be two vouchers found");
        assertEquals("VOUCHER2024", foundVouchers.get(0).getVoucherCode(), "The first voucher code should match");
        assertEquals("VOUCHER2024", foundVouchers.get(1).getVoucherCode(), "The second voucher code should match");
    }

    @Test
    public void testFindVoucherByUniqueCode() {
        Voucher voucher = new Voucher();
        voucher.setUniqueCode("UNIQUE123");
        when(voucherRepository.findByUniqueCode("UNIQUE123")).thenReturn(Optional.of(voucher));

        Optional<Voucher> foundVoucher = voucherService.findVoucherByUniqueCode("UNIQUE123");
        assertTrue(foundVoucher.isPresent());
        assertEquals("UNIQUE123", foundVoucher.get().getUniqueCode());
    }

    @Test
    public void testRedeemVoucherById() {
        Voucher voucher = new Voucher();
        voucher.setId("testId");
        voucher.setRedeemed(false); // Assegura que o voucher não foi resgatado ainda

        // Simula a busca do voucher no repositório
        when(voucherRepository.findById("testId")).thenReturn(Optional.of(voucher));

        // Ação
        Optional<Voucher> redeemedVoucher = voucherService.redeemVoucherById("testId");

        // Verificações
        assertTrue(redeemedVoucher.isPresent());
        assertNotNull(redeemedVoucher.get().getUsedDate()); // Verifica se a data de uso não é nula
        assertTrue(redeemedVoucher.get().isRedeemed()); // Verifica se o voucher foi marcado como resgatado
        verify(voucherRepository, times(1)).save(voucher); // Verifica se o método save foi chamado
    }

}
