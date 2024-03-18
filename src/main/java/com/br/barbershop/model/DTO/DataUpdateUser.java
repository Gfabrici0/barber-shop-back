package com.br.barbershop.model.DTO;

import jakarta.validation.constraints.Pattern;

import java.util.List;

public record DataUpdateUser(
    @Pattern(regexp = "^\\d{2}\\d{8,9}$", message = "Número de telefone celular inválido.")
    String phoneNumber,
    List<DataUpdateAddress> address
) {
}
