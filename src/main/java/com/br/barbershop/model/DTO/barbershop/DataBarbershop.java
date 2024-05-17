package com.br.barbershop.model.DTO.barbershop;

import com.br.barbershop.model.DTO.address.DataAddress;
import com.br.barbershop.model.DTO.user.DataUser;
import com.br.barbershop.model.entity.Barbershop;

import java.util.List;

public record DataBarbershop(
  String ownerName,
  String corporateName,
  String tradeName,
  String email,
  List<DataAddress> addresses,
  List<DataUser> users
) {
  public DataBarbershop(Barbershop barbershop) {
    this(
        barbershop.getOwnerName(),
        barbershop.getCorporateName(),
        barbershop.getTradeName(),
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
