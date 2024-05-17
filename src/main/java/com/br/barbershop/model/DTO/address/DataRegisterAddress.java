package com.br.barbershop.model.DTO.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DataRegisterAddress(
    @NotBlank
    @Pattern(regexp = "^\\d{5}\\d{3}$", message = "CEP não deve conter hífen")
    String addressNumber,
    @NotBlank
    String addressStreet,
    @NotBlank
    String addressCity
) {
}
