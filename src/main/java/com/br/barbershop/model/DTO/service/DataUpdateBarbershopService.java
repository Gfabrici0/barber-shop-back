package com.br.barbershop.model.DTO.service;

import java.math.BigDecimal;

public record DataUpdateBarbershopService(
  String serviceName,
  String description,
  BigDecimal value
) {}
