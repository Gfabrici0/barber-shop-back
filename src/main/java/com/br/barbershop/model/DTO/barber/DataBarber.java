package com.br.barbershop.model.DTO.barber;

import com.br.barbershop.model.DTO.barbershop.DataBarbershopWithoudUser;
import com.br.barbershop.model.DTO.user.DataUser;
import com.br.barbershop.model.entity.Barber;

import java.time.LocalDate;
import java.util.UUID;

public record DataBarber(
  UUID id,
  String email,
  String username,
  String document,
  LocalDate dateOfBirth,
  DataUser user,
  DataBarbershopWithoudUser barbershop
) {
  public DataBarber(Barber barber) {
    this(
      barber.getId(),
      barber.getUser().getEmail(),
      barber.getUser().getUsername(),
      barber.getUser().getDocument(),
      barber.getUser().getDateOfBirth(),
      new DataUser(barber.getUser()),
      new DataBarbershopWithoudUser(barber.getBarbershop())
    );
  }
}
