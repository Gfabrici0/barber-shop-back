package com.br.barbershop.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

public record DataRegisterUser(
    @Email
    @NotBlank
    String email,
    @NotBlank
    @Size(min = 3, max = 100)
    String username,
    @NotBlank
    String password,
    @CPF
    @NotBlank
    String document,
    @NotBlank
    String phoneNumber,
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    Date dateOfBirth,
    @NotNull
    @Valid
    DataAdress dataAdress
) {
}
