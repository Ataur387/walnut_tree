package com.example.blog.DTO.UserDTO;

import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public class UserResponseDTO extends UserBaseDTO{
    @NotNull
    private OffsetDateTime dateOfBirth;
    private Long id;

    public OffsetDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(OffsetDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
