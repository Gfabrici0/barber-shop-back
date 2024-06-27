package com.br.barbershop.model.DTO.scheduling;

import com.br.barbershop.enumeration.SchedulingStatusEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public record DataUpdateScheduling(
  LocalDateTime date,
  UUID barberId,
  SchedulingStatusEnum status,
  UUID serviceId
) {}
