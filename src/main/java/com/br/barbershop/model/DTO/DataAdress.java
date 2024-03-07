package com.br.barbershop.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DataAdress(
    @NotBlank
    @Pattern(regexp = "^\\d{5}-?\\d{3}$")
    String addressNumber,
    @NotBlank
    String addressStreet,
    @NotBlank
    String addressCity
) {
}
