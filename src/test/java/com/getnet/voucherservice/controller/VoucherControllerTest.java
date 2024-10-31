package com.getnet.voucherservice.controller;

import com.getnet.voucherservice.model.Voucher;
import com.getnet.voucherservice.security.JwtUtil;
import com.getnet.voucherservice.service.VoucherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VoucherController.class)
public class VoucherControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private VoucherService voucherService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UserDetailsService userDetailsService;

    private String token;

    @BeforeEach
    public void setUp() {
        UserDetails userDetails = User.withUsername("admin")
                .password("password")
                .roles("USER").build();

        when(userDetailsService.loadUserByUsername("admin")).thenReturn(userDetails);
        token = jwtUtil.generateToken(userDetails);
    }

    @Test
    void testCreateVoucher() throws Exception {
        Voucher voucher = new Voucher();
        voucher.setVoucherCode("NEWCODE");

        when(voucherService.saveVoucher(any(Voucher.class))).thenReturn(voucher);

        mockMvc.perform(post("/api/vouchers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"voucherCode\": \"NEWCODE\"}") 
                .header("Authorization", "Bearer " + getToken()))
            	.andExpect(MockMvcResultMatchers.status().isOk())
            	.andExpect(MockMvcResultMatchers.jsonPath("$.voucherCode").value("NEWCODE"));
    }

    @Test
    public void testGetVoucherById() throws Exception {
        Voucher voucher = new Voucher();
        voucher.setId("TESTID");
        voucher.setVoucherCode("VOUCHERCODE");

        when(voucherService.findVoucherById("TESTID")).thenReturn(Optional.of(voucher));

        mockMvc.perform(get("/api/vouchers/TESTID")
                .header("Authorization", "Bearer " + token)) // Authorization header
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.voucherCode").value("VOUCHERCODE"));
    }

    @Test
    public void testGetVoucherByUniqueCode() throws Exception {
        Voucher voucher = new Voucher();
        voucher.setUniqueCode("UNIQUE123");

        when(voucherService.findVoucherByUniqueCode("UNIQUE123")).thenReturn(Optional.of(voucher));

        mockMvc.perform(get("/api/vouchers/by-unique-code/UNIQUE123")
                .header("Authorization", "Bearer " + token))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.uniqueCode").value("UNIQUE123"));
    }
}
