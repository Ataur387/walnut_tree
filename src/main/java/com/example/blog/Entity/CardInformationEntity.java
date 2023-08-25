package com.example.blog.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cardInfo")
public class CardInformationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cardHolderName;
    private String cardNumber;
    @ManyToOne
    @JoinColumn(name = "pro_user_id")
    private ProUserEntity cardHolder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public ProUserEntity getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(ProUserEntity cardHolder) {
        this.cardHolder = cardHolder;
    }
}
