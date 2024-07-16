package com.br.barbershop.model.DTO.barber;

import java.util.List;
import java.util.UUID;

public record BarberAvailabilityReport (
    UUID barberId,
    List<BarberAvailability> availabilities
) {

}
