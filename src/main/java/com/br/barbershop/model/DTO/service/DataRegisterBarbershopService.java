package com.br.barbershop.model.DTO.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record DataRegisterBarbershopService(
  @NotNull
  UUID barbershopId,

  @NotNull
  UUID barberId,

  @NotBlank
  String serviceName,

  @NotBlank
  String description,

  @NotNull
  BigDecimal value
) {}
