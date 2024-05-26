package com.br.barbershop.model.DTO.user;

import com.br.barbershop.model.DTO.address.DataAddress;
import com.br.barbershop.model.DTO.role.DataRole;
import com.br.barbershop.model.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record DataUser(
    UUID id,
    String email,
    String username,
    String document,
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dateOfBirth,
    String phoneNumber,
    List<DataRole> role,
    List<DataAddress> address
) {
  public DataUser(User user) {
    this(
      user.getId(),
      user.getEmail(),
      user.getRealUsername(),
      user.getDocument(),
      user.getDateOfBirth(),
      user.getPhoneNumber(),
      user.getUserRoles().stream().map(
        userRole -> new DataRole(userRole.getRole())
      ).toList(),
      user.getUserAddresses().stream().map(
        userAddress -> new DataAddress(userAddress.getAddress())
      ).toList()
    );
  }
}
