package com.br.barbershop.model.DTO.barber;

import java.time.LocalDate;
import java.util.UUID;

public record BarberWithoutUser(
  UUID id,
  String email,
  String username,
  String document,
  LocalDate dateOfBirth
) {}