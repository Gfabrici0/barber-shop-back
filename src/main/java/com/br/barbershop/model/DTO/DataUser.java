package com.br.barbershop.model.DTO;

import com.br.barbershop.model.entity.User;

import java.util.Date;
import java.util.UUID;

public record DataUser(
    UUID id,
    String email,
    String username,
    String document,
    Date dateOfBirth
) {
  public DataUser(User user) {
    this(user.getId(), user.getEmail(), user.getUsername(), user.getDocument(), user.getDateOfBirth());
  }
}
