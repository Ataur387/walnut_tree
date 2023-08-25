package com.example.blog.DTO.UserDTO;

import com.example.blog.Entity.RoleType;
import com.example.blog.Entity.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public class UserCreateDTO extends UserBaseDTO{
    @NotNull
    private String userName;
    @NotNull
    private String password;
    @NotNull
    private String repeatPassword;
    @NotNull
    private OffsetDateTime dateOfBirth;
    private String subscriptionType;
    public RoleType roleType;
    private BillingAddressDTO billingAddress;
    private CardInformationDTO cardInformationDTO;

    public CardInformationDTO getCardInformation() {
        return cardInformationDTO;
    }

    public void setCardInformation(CardInformationDTO cardInformationDTO) {
        this.cardInformationDTO = cardInformationDTO;
    }

    public BillingAddressDTO getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddressDTO billingAddress) {
        this.billingAddress = billingAddress;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public OffsetDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(OffsetDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
