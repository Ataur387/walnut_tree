package com.example.blog.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.List;
@Entity
public class ProUserEntity extends UserEntity{
    @OneToMany
    private List<BillingAddressEntity> billingAddress;
    @OneToMany
    private List<CardInformationEntity> cardInformation;

    public List<BillingAddressEntity> getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(List<BillingAddressEntity> billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<CardInformationEntity> getCardInformation() {
        return cardInformation;
    }

    public void setCardInformation(List<CardInformationEntity> cardInformation) {
        this.cardInformation = cardInformation;
    }
}
