package com.br.barbershop.model.DTO.service;

import com.br.barbershop.model.entity.Barbershop;
import com.br.barbershop.model.entity.Service;

import java.util.UUID;

public record DataBarbershopService(
  UUID barbershopId,
  String tradeName,
  DataService dataService
) {
  public DataBarbershopService(Barbershop barbershop, Service service) {
    this(
      barbershop.getId(),
      barbershop.getTradeName(),
      new DataService(service)
    );
  }
}
