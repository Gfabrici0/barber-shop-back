package com.br.barbershop.model.DTO.barbershop;

import com.br.barbershop.model.DTO.address.DataAddress;
import com.br.barbershop.model.DTO.user.DataUser;
import com.br.barbershop.model.entity.Barbershop;

import java.util.List;
import java.util.UUID;

public record DataBarbershop(
  UUID id,
  String ownerName,
  String corporateName,
  String tradeName,
  String document,
  String email,
  List<DataAddress> addresses,
  List<DataUser> users
) {
  public DataBarbershop(Barbershop barbershop) {
    this(
      barbershop.getId(),
      barbershop.getOwnerName(),
      barbershop.getCorporateName(),
      barbershop.getTradeName(),
      barbershop.getDocument(),
      barbershop.getEmail(),
      barbershop.getBarbershopAddresses().stream()
        .map(barbershopAddress -> new DataAddress(barbershopAddress.getAddress()))
        .toList(),
      barbershop.getBarbershopUsers().stream()
        .map(barbershopUser -> new DataUser(barbershopUser.getUser()))
        .toList()
    );
  }
}
