package com.br.barbershop.model.DTO.scheduling;

import com.br.barbershop.model.DTO.barber.DataBarber;
import com.br.barbershop.model.DTO.barbershop.DataBarbershopWithoudUser;
import com.br.barbershop.model.DTO.service.DataService;
import com.br.barbershop.model.DTO.user.DataUser;
import com.br.barbershop.model.entity.*;

import java.time.LocalDateTime;
import java.util.UUID;

public record DataScheduling(
  UUID id,
  LocalDateTime date,
  SchedulingStatus status,
  DataUser user,
  DataBarbershopWithoudUser barbershop,
  DataService service,
  DataBarber barber,
  LocalDateTime createdAt,
  LocalDateTime updatedAt
) {
  public DataScheduling(Scheduling scheduling) {
    this(
      scheduling.getId(),
      scheduling.getDate(),
      scheduling.getStatus(),
      new DataUser(scheduling.getUser()),
      new DataBarbershopWithoudUser(scheduling.getBarbershop()),
      new DataService(scheduling.getService()),
      new DataBarber(scheduling.getBarber()),
      scheduling.getCreatedAt(),
      scheduling.getUpdatedAt()
    );
  }
}