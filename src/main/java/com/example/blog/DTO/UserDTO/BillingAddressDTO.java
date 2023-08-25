package com.example.blog.DTO.UserDTO;

public class BillingAddressDTO {
    private String address;
    private String city;
    private String postCode;
    private String id;

    public BillingAddressDTO(String address, String city, String postCode) {
        this.address = address;
        this.city = city;
        this.postCode = postCode;
    }

    public BillingAddressDTO(String id) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
