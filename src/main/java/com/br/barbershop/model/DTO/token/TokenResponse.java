package com.br.barbershop.model.DTO.token;

import java.util.UUID;

public record TokenResponse(
    String token,
    String email,
    UUID id,
    String username,
    String document,
    String role
) {
}
