package com.br.barbershop.model.DTO.service;

import com.br.barbershop.model.entity.Barbershop;
import com.br.barbershop.model.entity.Service;

import java.util.List;
import java.util.UUID;

public record ListBarbershopService(
    UUID barbershopId,
    String tradeName,
    List<DataService> services
) {
  public ListBarbershopService(Barbershop barbershop, List<Service> services) {
    this(
      barbershop.getId(),
      barbershop.getTradeName(),
      services.stream().map(
        DataService::new
      ).toList()
    );
  }
}
