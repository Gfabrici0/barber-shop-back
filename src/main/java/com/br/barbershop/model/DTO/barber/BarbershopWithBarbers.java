package com.br.barbershop.model.DTO.barber;

import com.br.barbershop.model.entity.Barbershop;

import java.util.List;
import java.util.UUID;

public record BarbershopWithBarbers(
  UUID id,
  String barbershopName,
  String document,
  String email,
  List<BarberWithoutUser> barbers
) {
  public BarbershopWithBarbers(Barbershop barbershop, List<BarberWithoutUser> barbers) {
    this(
      barbershop.getId(),
      barbershop.getTradeName(),
      barbershop.getDocument(),
      barbershop.getEmail(),
      barbers
    );
  }
}
