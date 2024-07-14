package com.br.barbershop.model.DTO.barbershop;

import com.br.barbershop.model.DTO.address.DataRegisterAddress;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDate;

public record DataRegisterBarbershop(
  @NotBlank
  String ownerName,

  @NotBlank
  String tradeName,

  @NotBlank
  String corporateName,

  @NotBlank
  @Email
  String email,

  @NotBlank
  String password,

  @NotBlank
  @CNPJ
  String document,

  @NotBlank
  @Pattern(regexp = "^\\d{2}\\d{8,9}$", message = "Número de telefone celular inválido.")
  String phoneNumber,

  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd")
  LocalDate dateOfBirth,

  @NotNull
  @Valid
  DataRegisterAddress barbershopAddress,

  @NotNull
  @Valid
  DataRegisterAddress personalAddress
) {
}