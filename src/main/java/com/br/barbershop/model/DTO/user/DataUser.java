package com.br.barbershop.model.DTO.user;

import com.br.barbershop.model.DTO.address.DataAddress;
import com.br.barbershop.model.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record DataUser(
    UUID id,
    String email,
    String username,
    String document,
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dateOfBirth,
    String phoneNumber,
    List<DataAddress> address
) {
  public DataUser(User user) {
    this(
        user.getId(),
        user.getEmail(),
        user.getRealUsername(),
        user.getFormattedDocument(),
        user.getDateOfBirth(),
        user.getFormattedPhoneNumber(),
        user.getUserAddresses().stream().map(
            userAddress -> new DataAddress(userAddress.getAddress())
        ).collect(Collectors.toList())
    );
  }
}
