package com.br.barbershop.model.DTO.user;

import com.br.barbershop.model.DTO.address.DataUpdateAddress;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record DataUpdateUser(
    String username,
    String password,
    @Pattern(regexp = "^\\d{2}\\d{8,9}$", message = "Número de telefone celular inválido.")
    String phoneNumber,
    List<DataUpdateAddress> address
) {
}
