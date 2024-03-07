package com.br.barbershop.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record DataUpdateUser(
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date dateOfBirth
) {
}
