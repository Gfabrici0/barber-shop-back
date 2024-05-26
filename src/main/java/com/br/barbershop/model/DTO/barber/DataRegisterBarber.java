package com.br.barbershop.model.DTO.barber;

import com.br.barbershop.model.DTO.address.DataRegisterAddress;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.UUID;

public record DataRegisterBarber(

  @NotNull
  UUID barbershopId,

  @NotBlank
  @Email
  String email,

  @NotBlank
  String password,

  @NotBlank
  @Size(min = 3, max = 100)
  String username,

  @NotBlank
  @Pattern(regexp = "^\\d{2}\\d{8,9}$", message = "Número de telefone celular inválido.")
  String phoneNumber,

  @NotBlank
  @CPF
  String document,

  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd")
  LocalDate dateOfBirth,

  @NotNull
  @Valid
  DataRegisterAddress address
) {}
