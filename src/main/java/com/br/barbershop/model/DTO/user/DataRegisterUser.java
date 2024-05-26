package com.br.barbershop.model.DTO.user;

import com.br.barbershop.enumeration.RoleEnum;
import com.br.barbershop.model.DTO.address.DataRegisterAddress;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record DataRegisterUser(
  @Email
  @NotBlank
  String email,
  @NotBlank
  @Size(min = 3, max = 100)
  String username,
  @NotBlank
  String password,
  @CPF
  @NotBlank
  String document,
  @NotNull
  RoleEnum role,
  @NotBlank
  @Pattern(regexp = "^\\d{2}\\d{8,9}$", message = "Número de telefone celular inválido.")
  String phoneNumber,
  @JsonFormat(pattern = "yyyy-MM-dd")
  @NotNull
  LocalDate dateOfBirth,
  @NotNull
  @Valid
  DataRegisterAddress address
) {}
