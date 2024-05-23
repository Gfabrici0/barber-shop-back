package com.br.barbershop.model.DTO.barbershop;

import com.br.barbershop.model.DTO.address.DataAddress;
import com.br.barbershop.model.entity.Barbershop;

import java.util.List;
import java.util.UUID;

public record DataBarbershopWithoudUser(
    UUID id,
    String ownerName,
    String corporateName,
    String tradeName,
    String document,
    String email,
    List<DataAddress> addresses
) {
  public DataBarbershopWithoudUser(Barbershop barbershop) {
    this(
        barbershop.getId(),
        barbershop.getOwnerName(),
        barbershop.getCorporateName(),
        barbershop.getTradeName(),
        barbershop.getDocument(),
        barbershop.getEmail(),
        barbershop.getBarbershopAddresses().stream()
            .map(barbershopAddress -> new DataAddress(barbershopAddress.getAddress()))
            .toList()
    );
  }
}
