package com.br.barbershop.model.DTO.barber;

import java.util.List;

public record BarberAvailability (
    String day,
    List<String> hours
){}
