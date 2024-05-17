package com.br.barbershop.model.DTO.address;

import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record DataUpdateAddress(
    UUID id,
    @Pattern(regexp = "^\\d{5}\\d{3}$", message = "CEP não deve conter hífen")
    String addressNumber,
    String addressStreet,
    String addressCity
) {
}
