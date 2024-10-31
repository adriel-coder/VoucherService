package com.getnet.voucherservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "vouchers")
public class Voucher {
    @Id
    private String id;

    private String voucherCode;
    
    @Indexed(unique = true)
    private String uniqueCode;
    private boolean redeemed;

    private String recipientId; 
    private String specialOfferId; 

    private LocalDate expiryDate;
    private LocalDate usedDate;

    public Voucher() {
        initializeUniqueCode();
    }

    private void initializeUniqueCode() {
        if (this.uniqueCode == null || this.uniqueCode.isEmpty()) {
            this.uniqueCode = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8).toUpperCase();
        }
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getRecipientId() { // Getter para o ID do Recipient
        return recipientId;
    }

    public void setRecipientId(String recipientId) { // Setter para o ID do Recipient
        this.recipientId = recipientId;
    }

    public String getSpecialOfferId() { // Getter para o ID do SpecialOffer
        return specialOfferId;
    }

    public void setSpecialOfferId(String specialOfferId) { // Setter para o ID do SpecialOffer
        this.specialOfferId = specialOfferId;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getUsedDate() {
        return usedDate;
    }

    public void setUsedDate(LocalDate usedDate) {
        this.usedDate = usedDate;
    }

    public boolean isRedeemed() {
        return redeemed;
    }

    public void setRedeemed(boolean redeemed) {
        this.redeemed = redeemed;
    }
}
