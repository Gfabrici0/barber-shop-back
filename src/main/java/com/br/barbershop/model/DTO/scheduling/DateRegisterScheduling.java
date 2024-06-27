package com.br.barbershop.model.DTO.scheduling;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record DateRegisterScheduling(
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime date,

    @NonNull
    UUID userId,

    @NonNull
    UUID barbershopId,

    @NonNull
    UUID serviceId,

    @NonNull
    UUID barberId
) {}
