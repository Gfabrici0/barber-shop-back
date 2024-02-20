package com.br.barbershop.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record DataRegisterClient(
    @NotBlank
    String username,
    @NotBlank
    String password,
    @CPF
    @NotBlank
    String document,
    @Email
    @NotBlank
    String email,
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotBlank
    String dateOfBirth,
    @NotBlank
    String addressStreet,
    @NotBlank
    String addressNumber,
    @NotBlank
    String addressCity
) {
}
