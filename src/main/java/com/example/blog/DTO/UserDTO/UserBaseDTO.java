package com.example.blog.DTO.UserDTO;

import com.example.blog.Entity.UserStatus;
import jakarta.validation.constraints.NotBlank;

public class UserBaseDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private UserStatus status = UserStatus.PENDING;
    private String uuid;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
