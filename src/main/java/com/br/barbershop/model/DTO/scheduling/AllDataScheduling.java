package com.br.barbershop.model.DTO.scheduling;

import com.br.barbershop.model.DTO.barber.BarberWithoutBarbershop;
import com.br.barbershop.model.DTO.barbershop.DataBarbershopWithoudUser;
import com.br.barbershop.model.DTO.service.DataService;
import com.br.barbershop.model.DTO.user.DataUser;
import com.br.barbershop.model.entity.Scheduling;
import com.br.barbershop.model.entity.SchedulingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record AllDataScheduling(
  UUID id,
  LocalDateTime date,
  SchedulingStatus status,
  DataUser userId,
  DataBarbershopWithoudUser barbershop,
  DataService service,
  BarberWithoutBarbershop barber,
  LocalDateTime createdAt,
  LocalDateTime updatedAt
) {
  public AllDataScheduling(Scheduling scheduling) {
    this(
      scheduling.getId(),
      scheduling.getDate(),
      scheduling.getStatus(),
      new DataUser(scheduling.getUser()),
      new DataBarbershopWithoudUser(scheduling.getBarbershop()),
      new DataService(scheduling.getService()),
      new BarberWithoutBarbershop(scheduling.getBarber()),
      scheduling.getCreatedAt(),
      scheduling.getUpdatedAt()
    );
  }
}