package com.br.barbershop.model.DTO.service;

import com.br.barbershop.model.entity.Service;

import java.math.BigDecimal;
import java.util.UUID;

public record DataService(
  UUID id,
  String serviceName,
  String description,
  BigDecimal value
) {
  public DataService(Service service) {
    this(
      service.getId(),
      service.getServiceName(),
      service.getDescription(),
      service.getValue()
    );
  }
}
