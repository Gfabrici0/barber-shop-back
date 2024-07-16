package com.br.barbershop.model.DTO.scheduling;

import com.br.barbershop.enumeration.SchedulingStatusEnum;
import jakarta.validation.constraints.NotNull;

public record DataUpdateStatusScheduling(
    @NotNull
    SchedulingStatusEnum status
) {}
