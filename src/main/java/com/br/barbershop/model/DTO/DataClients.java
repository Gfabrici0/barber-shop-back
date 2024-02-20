package com.br.barbershop.model.DTO;

import com.br.barbershop.model.entity.User;

import java.util.UUID;

public record DataClients(
    UUID id,
    String username,
    String document,
    String email,
    String dateOfBirth,
    String addressStreet,
    String addressNumber,
    String addressCity
) {
  public DataClients(User user) {
    this(user.getId(), user.getUsername(), user.getDocument(), user.getEmail(), user.getDateOfBirth(), user.getAddressStreet(), user.getAddressNumber(), user.getAddressCity());
  }
}
