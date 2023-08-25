package com.example.blog.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "billlingaddress")
public class BillingAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String address;
    private String city;
    private String postCode;
    @ManyToMany(mappedBy = "billingAddress")
    private List<ProUserEntity> proUsers;

    public BillingAddressEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public List<ProUserEntity> getProUsers() {
        return proUsers;
    }

    public void setProUsers(List<ProUserEntity> proUsers) {
        this.proUsers = proUsers;
    }
}
