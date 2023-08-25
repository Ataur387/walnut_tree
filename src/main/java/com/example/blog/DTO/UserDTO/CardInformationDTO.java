package com.example.blog.DTO.UserDTO;

public class CardInformationDTO {
    private String id;
    private String cardHolderName;
    private String cardNumber;

    public CardInformationDTO(String cardHolderName, String cardNumber) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
